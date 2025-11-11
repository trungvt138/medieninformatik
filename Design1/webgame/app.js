// ====== Data & Constants ======
const GENRES = ["Mystery","Fantasy","Science","History","Romance","Horror"]; // 6
const COLORS = ["Red","Yellow","Green","Blue","Purple","Teal"]; // 6
const COLOR_RGB = {
  Red:   "rgb(220,53,69)",
  Yellow:"rgb(255,193,7)",
  Green: "rgb(40,167,69)",
  Blue:  "rgb(0,123,255)",
  Purple:"rgb(111,66,193)",
  Teal:  "rgb(32,201,151)"
};

const SIZE = 6; // 6x6 board

// ====== Game State ======
const state = {
  board: null,          // 2D array SIZE×SIZE of tiles or null
  supply: [],
  display: [],
  focuses: ["genre","color"], // p1,p2, set via modal
  current: 0,           // player index 0/1
  phase: "place",       // start with place so there's something to click
  selected: null,       // {r,c} selected for sliding
  validDests: new Set(),// set of "r,c" strings
  selectedDisplay: null // index in display
};

// ====== Utilities ======
function makeDeck(){
  const deck = [];
  for(const g of GENRES){
    for(const c of COLORS){ deck.push({genre:g, color:c}); }
  }
  return deck;
}
function shuffle(a){
  for(let i=a.length-1;i>0;i--){ const j=Math.floor(Math.random()*(i+1)); [a[i],a[j]]=[a[j],a[i]]; }
  return a;
}
function emptyBoard(){ return Array.from({length:SIZE},()=>Array(SIZE).fill(null)); }
function inBounds(r,c){ return r>=0 && r<SIZE && c>=0 && c<SIZE; }
function key(r,c){ return `${r},${c}`; }

// ====== Setup & New Game ======
function newGame(){
  state.board = emptyBoard();
  state.supply = shuffle(makeDeck());
  state.display = [];
  for(let i=0;i<6;i++) drawFromSupplyToDisplay();
  state.current = 0;
  state.phase = "place";
  state.selected = null; state.validDests.clear(); state.selectedDisplay = null;
  updateSupplyBadge();
  renderDisplay();
  render();
  showFocusDialog();
  setStatus();
}

function showFocusDialog(){
  const dlg = document.getElementById('focusDialog');
  dlg.showModal();
  document.getElementById('startBtn').onclick = () => {
    const p1 = document.querySelector('input[name="p1"]:checked').value;
    const p2 = document.querySelector('input[name="p2"]:checked').value;
    state.focuses = [p1,p2];
    dlg.close();
    setStatus();
  };
}

function drawFromSupplyToDisplay(){
  if(state.supply.length>0 && state.display.length<6){
    state.display.push(state.supply.pop());
  }
}

// ====== Canvas & Rendering ======
const canvas = document.getElementById('board');
const ctx = canvas.getContext('2d');

function resizeCanvas(){
  render();
}
window.addEventListener('resize', resizeCanvas);

function render(){
  const W = canvas.width, H = canvas.height;
  ctx.clearRect(0,0,W,H);
  // background
  ctx.fillStyle = '#0d0f14'; ctx.fillRect(0,0,W,H);
  // outer frame
  roundRect(ctx, 8,8,W-16,H-16, 18, '#11141b', '#2a2f3a', 2);
  // grid
  const pad = 40; const size = W - pad*2; const cell = size / SIZE;
  // labels
  ctx.fillStyle = '#c9ccd3'; ctx.font = '14px system-ui'; ctx.textAlign='center'; ctx.textBaseline='middle';
  for(let r=0;r<SIZE;r++){
    ctx.fillText(String(r+1), pad-18, pad + r*cell + cell/2);
    ctx.fillText(String(r+1), pad+size+18, pad + r*cell + cell/2);
  }
  for(let c=0;c<SIZE;c++){
    ctx.fillText(String.fromCharCode(65+c), pad + c*cell + cell/2, pad-18);
    ctx.fillText(String.fromCharCode(65+c), pad + c*cell + cell/2, pad+size+18);
  }
  // grid lines
  ctx.strokeStyle = '#2a2f3a'; ctx.lineWidth = 2;
  for(let i=0;i<=SIZE;i++){
    const y = pad + i*cell; ctx.beginPath(); ctx.moveTo(pad, y); ctx.lineTo(pad+size, y); ctx.stroke();
    const x = pad + i*cell; ctx.beginPath(); ctx.moveTo(x, pad); ctx.lineTo(x, pad+size); ctx.stroke();
  }
  // highlight valid destinations
  for(const k of state.validDests){
    const [r,c] = k.split(',').map(Number);
    const x = pad + c*cell; const y = pad + r*cell;
    ctx.fillStyle = 'rgba(110,168,254,0.18)'; ctx.fillRect(x+2,y+2,cell-4,cell-4);
    ctx.strokeStyle = '#6ea8fe'; ctx.lineWidth = 2; ctx.strokeRect(x+2,y+2,cell-4,cell-4);
  }
  // draw tiles
  for(let r=0;r<SIZE;r++){
    for(let c=0;c<SIZE;c++){
      const t = state.board[r][c];
      if(!t) continue;
      const x = pad + c*cell; const y = pad + r*cell;
      drawTile(ctx, x,y, cell, t, state.selected && state.selected.r===r && state.selected.c===c);
    }
  }
}

function roundRect(ctx, x,y,w,h, r, fill, stroke, lw=1){
  ctx.beginPath();
  ctx.moveTo(x+r,y);
  ctx.arcTo(x+w,y, x+w,y+h, r);
  ctx.arcTo(x+w,y+h, x,y+h, r);
  ctx.arcTo(x,y+h, x,y, r);
  ctx.arcTo(x,y, x+w,y, r);
  if(fill){ ctx.fillStyle = fill; ctx.fill(); }
  if(stroke){ ctx.lineWidth = lw; ctx.strokeStyle = stroke; ctx.stroke(); }
}

function drawTile(ctx, x, y, size, tile, selected=false){
  // card background
  roundRect(ctx, x+6, y+6, size-12, size-12, 14, '#f4f5f6', '#0f1218', 2);
  // header text
  ctx.fillStyle = '#10141b'; ctx.font = 'bold 14px system-ui'; ctx.textAlign='center'; ctx.textBaseline='middle';
  ctx.fillText(tile.genre, x + size/2, y + size*0.14);

  // icon area
  const cx = x + size/2, cy = y + size*0.56; const s = size*0.8;
  drawIcon(ctx, tile, cx, cy, s);

  // footer text
  ctx.fillStyle = '#3a3f4a'; ctx.font = '12px system-ui';
  ctx.fillText(tile.color + ' Jacket', x + size/2, y + size*0.88);

  if(selected){
    ctx.strokeStyle = '#6ea8fe'; ctx.lineWidth = 3; ctx.strokeRect(x+4,y+4,size-8,size-8);
  }
}

function drawIcon(ctx, tile, cx, cy, s){
  const color = COLOR_RGB[tile.color];
  ctx.save();
  // shadow
  ctx.fillStyle = 'rgba(0,0,0,0.08)'; ctx.beginPath(); ctx.ellipse(cx, cy + s*0.25, s*0.23, s*0.06, 0, 0, Math.PI*2); ctx.fill();
  // choose by genre
  switch(tile.genre){
    case 'Mystery': drawMagnifier(ctx, cx, cy, s, color); break;
    case 'Fantasy': drawStar(ctx, cx, cy, s, color); break;
    case 'Science': drawAtom(ctx, cx, cy, s, color); break;
    case 'History': drawBook(ctx, cx, cy, s, color); break;
    case 'Romance': drawHeart(ctx, cx, cy, s, color); break;
    case 'Horror': drawGhost(ctx, cx, cy, s, color); break;
  }
  ctx.restore();
}

// ====== Simple Icon Drawing ======
function drawMagnifier(ctx, cx, cy, s, color){
  const r = s*0.22;
  ctx.lineWidth = 6; ctx.strokeStyle = '#0f1218'; ctx.fillStyle = color;
  ctx.beginPath(); ctx.arc(cx, cy, r, 0, Math.PI*2); ctx.fill(); ctx.stroke();
  ctx.lineWidth = 10; ctx.strokeStyle = '#0f1218'; ctx.beginPath(); ctx.moveTo(cx+r*0.8, cy+r*0.8); ctx.lineTo(cx+r*1.6, cy+r*1.6); ctx.stroke();
  ctx.lineWidth = 3; ctx.strokeStyle = 'white'; ctx.beginPath(); ctx.arc(cx, cy, r*0.65, 0, Math.PI*2); ctx.stroke();
}
function drawStar(ctx, cx, cy, s, color){
  ctx.fillStyle = color; ctx.strokeStyle = '#0f1218'; ctx.lineWidth = 3;
  const pts = [];
  for(let i=0;i<10;i++){
    const ang = (-90 + i*36) * Math.PI/180;
    const rad = (i%2===0) ? s*0.25 : s*0.11;
    pts.push([cx + Math.cos(ang)*rad, cy + Math.sin(ang)*rad]);
  }
  ctx.beginPath(); ctx.moveTo(pts[0][0], pts[0][1]);
  for(let i=1;i<pts.length;i++) ctx.lineTo(pts[i][0], pts[i][1]);
  ctx.closePath(); ctx.fill(); ctx.stroke();
}
function drawAtom(ctx, cx, cy, s, color){
  ctx.lineWidth = 3; ctx.strokeStyle = color; ctx.fillStyle = color;
  const a = s*0.26, b = s*0.16;
  [0,60,120].forEach(rot=>{
    ctx.save(); ctx.translate(cx,cy); ctx.rotate(rot*Math.PI/180);
    ctx.beginPath();
    for(let t=0;t<=Math.PI*2+0.01;t+=0.04){ ctx.lineTo(Math.cos(t)*a, Math.sin(t)*b); }
    ctx.stroke(); ctx.restore();
  });
  ctx.fillStyle = '#0f1218'; ctx.beginPath(); ctx.arc(cx, cy, s*0.03, 0, Math.PI*2); ctx.fill();
  ctx.fillStyle = color; ctx.beginPath(); ctx.arc(cx+a, cy, 6, 0, Math.PI*2); ctx.fill();
  ctx.beginPath(); ctx.arc(cx-a, cy, 6, 0, Math.PI*2); ctx.fill();
}
function drawBook(ctx, cx, cy, s, color){
  const w = s*0.35, h = s*0.24;
  ctx.fillStyle = color; ctx.strokeStyle = '#0f1218'; ctx.lineWidth = 4;
  roundPath(ctx, cx-w, cy-h, w*2, h*2, 12); ctx.fill(); ctx.stroke();
  ctx.strokeStyle = 'white'; ctx.lineWidth = 3; ctx.beginPath(); ctx.moveTo(cx-w, cy); ctx.lineTo(cx+w, cy); ctx.stroke();
  ctx.strokeStyle = '#0f1218'; ctx.lineWidth = 3; ctx.beginPath(); ctx.moveTo(cx, cy-h); ctx.lineTo(cx, cy+h); ctx.stroke();
  ctx.fillStyle = '#e7e7ea'; ctx.fillRect(cx-w, cy+h-8, w*2, 10);
}
function roundPath(ctx,x,y,w,h,r){
  ctx.beginPath();
  ctx.moveTo(x+r,y); ctx.arcTo(x+w,y,x+w,y+h,r); ctx.arcTo(x+w,y+h,x,y+h,r); ctx.arcTo(x,y+h,x,y,r); ctx.arcTo(x,y,x+w,y,r);
}
function drawHeart(ctx, cx, cy, s, color){
  const r = s*0.17;
  ctx.fillStyle = color; ctx.strokeStyle = '#0f1218'; ctx.lineWidth = 4;
  ctx.beginPath();
  ctx.moveTo(cx, cy+r*1.8);
  ctx.bezierCurveTo(cx+r*1.2, cy+r*0.8, cx+r*1.2, cy-r*0.4, cx, cy-r*0.2);
  ctx.bezierCurveTo(cx-r*1.2, cy-r*0.4, cx-r*1.2, cy+r*0.8, cx, cy+r*1.8);
  ctx.fill(); ctx.stroke();
}
function drawGhost(ctx, cx, cy, s, color){
  const w = s*0.3, h = s*0.3;
  ctx.fillStyle = color; ctx.strokeStyle = '#0f1218'; ctx.lineWidth = 4;
  roundPath(ctx, cx-w, cy-h, w*2, h*1.4, 14); ctx.fill(); ctx.stroke();
  // scallops
  ctx.beginPath();
  for(let i=-2;i<=2;i++){
    const sx = cx + i*w/2.5; ctx.moveTo(sx-10, cy+h*0.4); ctx.arc(sx, cy+h*0.4, 10, Math.PI, 0);
  }
  ctx.fillStyle = color; ctx.fill(); ctx.stroke();
  // eyes
  ctx.fillStyle = '#0f1218'; ctx.beginPath(); ctx.arc(cx-10, cy-4, 5, 0, Math.PI*2); ctx.fill();
  ctx.beginPath(); ctx.arc(cx+10, cy-4, 5, 0, Math.PI*2); ctx.fill();
}

// ====== Input Handling ======
canvas.addEventListener('click', (e)=>{
  const rect = canvas.getBoundingClientRect();
  const px = (e.clientX - rect.left) * (canvas.width / rect.width);
  const py = (e.clientY - rect.top) * (canvas.height / rect.height);
  const pad = 40; const size = canvas.width - pad*2; const cell = size / SIZE;
  if(px<pad || py<pad || px>pad+size || py>pad+size) return; // outside grid
  const c = Math.floor((px - pad)/cell);
  const r = Math.floor((py - pad)/cell);
  onBoardClick(r,c);
});

function onBoardClick(r,c){
  if(state.phase === 'slide'){
    const t = state.board[r][c];
    if(t && !state.selected){
      state.selected = {r,c};
      computeValidDests(r,c);
    } else if(state.selected && state.validDests.has(key(r,c))){
      // perform slide
      const {r:sr,c:sc} = state.selected;
      state.board[r][c] = state.board[sr][sc];
      state.board[sr][sc] = null;
      state.selected = null; state.validDests.clear();
      state.phase = 'place'; state.selectedDisplay = null;
    } else {
      // click elsewhere cancels selection
      state.selected = null; state.validDests.clear();
    }
  } else if(state.phase === 'place'){
    if(state.board[r][c]===null && state.selectedDisplay!=null){
      // place selected display tile
      state.board[r][c] = state.display[state.selectedDisplay];
      state.display.splice(state.selectedDisplay,1);
      drawFromSupplyToDisplay();
      state.selectedDisplay = null;
      // end check
      if(boardFull()){
        endAndScore();
      } else {
        // next player
        state.current = 1 - state.current;
        state.phase = 'slide';
      }
    }
  }
  renderDisplay();
  render();
  setStatus();
}

function computeValidDests(r,c){
  state.validDests.clear();
  // scan in 4 directions until blocked; add empty cells with clear path
  const dirs = [[1,0],[-1,0],[0,1],[0,-1]];
  for(const [dr,dc] of dirs){
    let nr=r+dr, nc=c+dc;
    while(inBounds(nr,nc) && state.board[nr][nc]===null){
      state.validDests.add(key(nr,nc));
      nr+=dr; nc+=dc;
    }
  }
}

function boardFull(){
  for(let r=0;r<SIZE;r++) for(let c=0;c<SIZE;c++) if(!state.board[r][c]) return false;
  return true;
}

// ====== Display panel ======
function renderDisplay(){
  const cont = document.getElementById('display');
  cont.innerHTML='';
  state.display.forEach((t,idx)=>{
    const div = document.createElement('div');
    div.className = 'slot' + (state.selectedDisplay===idx ? ' sel':'' );
    div.title = `${t.genre} — ${t.color} Jacket`;
    const canv = document.createElement('canvas'); canv.width=120; canv.height=90;
    const c2 = canv.getContext('2d');
    // draw tiny tile
    drawTile(c2, 10, 5, 100, t, false);
    div.appendChild(canv);
    div.onclick = ()=>{
      if(state.phase!=='place') return;
      state.selectedDisplay = idx; renderDisplay(); setStatus();
    };
    cont.appendChild(div);
  });
  updateSupplyBadge();
}

function updateSupplyBadge(){
  document.getElementById('supplyBadge').textContent = `Supply: ${state.supply.length}`;
}

function setStatus(){
  const p = state.current+1;
  const focus = state.focuses[state.current]==='genre' ? 'Genres' : 'Colors';
  const phase = state.phase==='slide' ? 'Slide a book' : (state.selectedDisplay==null ? 'Place: select a display tile' : 'Place: click an empty cell');
  document.getElementById('status').textContent = `Player ${p} · Focus: ${focus} · ${phase}`;
}

// ====== Scoring ======
const SCORE_TABLE = {2:1,3:3,4:6,5:10,6:15};

function computeScoreFor(playerIdx){
  const focus = state.focuses[playerIdx];
  const seen = Array.from({length:SIZE},()=>Array(SIZE).fill(false));
  let total = 0; const groups = [];
  for(let r=0;r<SIZE;r++){
    for(let c=0;c<SIZE;c++){
      const t = state.board[r][c];
      if(!t || seen[r][c]) continue;
      // BFS for attribute
      const attr = focus==='genre' ? t.genre : t.color;
      const q=[[r,c]]; seen[r][c]=true; let sz=0; const cells=[[r,c]];
      while(q.length){
        const [cr,cc]=q.shift(); sz++;
        const nbs=[[1,0],[-1,0],[0,1],[0,-1]];
        for(const [dr,dc] of nbs){
          const nr=cr+dr, nc=cc+dc;
          if(!inBounds(nr,nc) || seen[nr][nc]) continue;
          const t2 = state.board[nr][nc];
          if(t2 && ((focus==='genre' && t2.genre===attr) || (focus==='color' && t2.color===attr))){
            seen[nr][nc]=true; q.push([nr,nc]); cells.push([nr,nc]);
          }
        }
      }
      if(sz>=2){
        const pts = SCORE_TABLE[sz] ?? (sz>6 ? 15 : 0);
        total += pts; groups.push({attr, size:sz, pts, cells});
      }
    }
  }
  return {total, groups, focus};
}

function endAndScore(){
  const s1 = computeScoreFor(0);
  const s2 = computeScoreFor(1);
  const win = s1.total===s2.total ? 'Draw!' : (s1.total>s2.total? 'Player 1 wins!' : 'Player 2 wins!');
  showScoreModal(s1,s2,win);
}

function showScoreModal(s1,s2,headline='Scores'){
  const box = document.getElementById('scoreContent');
  box.innerHTML = `
    <p><b>${headline}</b></p>
    <div class="hr"></div>
    <p><b>Player 1</b> — Focus: ${s1.focus==='genre'?'Genres':'Colors'} — <b>${s1.total}</b> pts</p>
    ${renderGroupList(s1.groups)}
    <div class="hr"></div>
    <p><b>Player 2</b> — Focus: ${s2.focus==='genre'?'Genres':'Colors'} — <b>${s2.total}</b> pts</p>
    ${renderGroupList(s2.groups)}
  `;
  document.getElementById('scoreDialog').showModal();
}

function renderGroupList(groups){
  if(!groups.length) return '<p class="hint">No scoring groups.</p>';
  return '<ul>' + groups.map(g=>`<li>${g.size} in ${g.attr} → ${g.pts} pts</li>`).join('') + '</ul>';
}

// ====== Buttons & Modals ======
document.getElementById('newBtn').onclick = newGame;
document.getElementById('rulesBtn').onclick = ()=>document.getElementById('rulesDialog').showModal();
document.getElementById('closeRules').onclick = ()=>document.getElementById('rulesDialog').close();
document.getElementById('scoreBtn').onclick = ()=>{
  const s1 = computeScoreFor(0); const s2 = computeScoreFor(1);
  showScoreModal(s1,s2,'Current Board');
};
document.getElementById('closeScore').onclick = ()=>document.getElementById('scoreDialog').close();

// ====== Kickoff ======
newGame();
