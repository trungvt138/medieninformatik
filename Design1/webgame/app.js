// ====== Toys, Colors, and Sliced Icon Files ======
const TOYS   = ["Robot","Dino","Car","Doll","Puzzle","Ball"];           // rows (top→bottom)
const COLORS = ["Red","Orange","Yellow","Green","Blue","Purple"];       // UI order

// Files live at: assets/sliced/_0000_Robot_Orange.png … _0035_Ball_Purple.png
// Folder index order (by color column):
// Orange: 0000..0005, Red: 0006..0011, Yellow: 0012..0017, Green: 0018..0023, Blue: 0024..0029, Purple: 0030..0035
const ICON_DIR = "assets/sliced";
const ICON_EXT = ".png";

// Base index for each color column in your folder
const COLOR_BASE = {
  Orange: 0,
  Red:    6,
  Yellow: 12,
  Green:  18,
  Blue:   24,
  Purple: 30
};

// Per-color toy order — after your rename, all columns use the same order:
const TOY_ORDER_BY_COLOR = {
  Orange: ["Robot","Dino","Car","Doll","Puzzle","Ball"],
  Red:    ["Robot","Dino","Car","Doll","Puzzle","Ball"],
  Yellow: ["Robot","Dino","Car","Doll","Puzzle","Ball"],
  Green:  ["Robot","Dino","Car","Doll","Puzzle","Ball"],
  Blue:   ["Robot","Dino","Car","Doll","Puzzle","Ball"],
  Purple: ["Robot","Dino","Car","Doll","Puzzle","Ball"]
};

function indexFor(toy, color) {
  const base = COLOR_BASE[color];
  const order = TOY_ORDER_BY_COLOR[color];
  if (base == null || !order) return -1;
  const pos = order.indexOf(toy);
  if (pos < 0) return -1;
  return base + pos; // 0..35
}
function fileForIndex(idx, toy, color) {
  return `${ICON_DIR}/_${String(idx).padStart(4,"0")}_${toy}_${color}${ICON_EXT}`;
}
const keyIcon = (toy, color) => `${toy}|${color}`;

// ====== Game constants ======
const SIZE = 6; // 6x6 board
const SCORE_TABLE = { 2:1, 3:3, 4:6, 5:10, 6:15 };

// ====== State ======
const state = {
  board: null,
  supply: [],
  display: [],
  focuses: ["genre","color"],  // 'genre' means toy-type groups; 'color' means color groups
  current: 0,
  phase: "place",
  selected: null,
  validDests: new Set(),
  selectedDisplay: null
};

// ====== Helpers ======
function makeDeck(){ const d=[]; for (const g of TOYS) for (const c of COLORS) d.push({genre:g,color:c}); return d; }
function shuffle(a){ for(let i=a.length-1;i>0;i--){ const j=Math.floor(Math.random()*(i+1)); [a[i],a[j]]=[a[j],a[i]]; } return a; }
function emptyBoard(){ return Array.from({length:SIZE},()=>Array(SIZE).fill(null)); }
function inBounds(r,c){ return r>=0 && r<SIZE && c>=0 && c<SIZE; }
function key(r,c){ return `${r},${c}`; }

// ====== DOM ======
const canvas = document.getElementById("board");
const ctx = canvas.getContext("2d");
const displayEl = document.getElementById("display");
const supplyBadge = document.getElementById("supplyBadge");

// ====== Preload sliced icons ======
const ICONS = new Map(); // Map<"Toy|Color", HTMLImageElement>
let iconsReady = false;

function preloadIcons(){
  const tasks = [];
  for (const toy of TOYS){
    for (const color of COLORS){
      const idx = indexFor(toy,color);           // 0..35 using folder mapping
      const src = fileForIndex(idx, toy, color); // e.g. _0000_Robot_Orange.png
      const img = new Image();
      ICONS.set(keyIcon(toy,color), img);
      tasks.push(new Promise(res => {
        img.onload  = res;
        img.onerror = () => { console.warn("Missing sprite:", src); res(); };
        img.src = src;
      }));
    }
  }
  return Promise.all(tasks).then(() => { iconsReady = true; });
}

// ====== New Game ======
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
  sizeBoardToContainer();
  if (iconsReady) render();
  showFocusDialog();
  setStatus();
}

function drawFromSupplyToDisplay(){
  if(state.supply.length>0 && state.display.length<6){
    state.display.push(state.supply.pop());
  }
}

function showFocusDialog(){
  const dlg = document.getElementById("focusDialog");
  if (!dlg || !dlg.showModal) return; // skip if dialog not present
  dlg.showModal();
  const startBtn = document.getElementById("startBtn");
  if (startBtn){
    startBtn.onclick = () => {
      const p1 = document.querySelector('input[name="p1"]:checked')?.value || "genre";
      const p2 = document.querySelector('input[name="p2"]:checked')?.value || "color";
      state.focuses = [p1,p2];
      dlg.close();
      setStatus();
    };
  }
}

// ====== Sizing ======
function sizeBoardToContainer(){
  const cont = document.getElementById("boardContainer");
  if (!cont) return;

  const isDesktop = window.matchMedia("(min-width: 900px)").matches;
  const MAX_BOARD = isDesktop ? 560 : 640;

  const availableW = Math.floor(cont.clientWidth);
  const header = document.querySelector("header");
  const mainPad = 32;
  const extraBottom = isDesktop ? 220 : 160;
  const availableH = Math.max(
    360,
    window.innerHeight - (header?.offsetHeight || 0) - mainPad - extraBottom
  );

  const target = Math.max(320, Math.floor(Math.min(availableW, availableH, MAX_BOARD)));
  const dpr = Math.min(window.devicePixelRatio || 1, 2);

  canvas.style.width  = target + "px";
  canvas.style.height = target + "px";
  canvas.width  = Math.floor(target * dpr);
  canvas.height = Math.floor(target * dpr);
}
window.addEventListener("resize", () => { sizeBoardToContainer(); render(); });

// ====== Rendering ======
function render(){
  const W = canvas.width, H = canvas.height;
  ctx.clearRect(0,0,W,H);

  // background + frame
  ctx.fillStyle = "#0d0f14"; ctx.fillRect(0,0,W,H);
  roundRect(ctx, 8,8, W-16, H-16, 18, "#11141b", "#2a2f3a", 2);

  // grid metrics
  const pad = 40; const size = W - pad*2; const cell = size / SIZE;

  // labels
  ctx.fillStyle = "#c9ccd3"; ctx.font = "14px system-ui"; ctx.textAlign="center"; ctx.textBaseline="middle";
  for(let r=0;r<SIZE;r++){
    ctx.fillText(String(r+1), pad-18, pad + r*cell + cell/2);
    ctx.fillText(String(r+1), pad+size+18, pad + r*cell + cell/2);
  }
  for(let c=0;c<SIZE;c++){
    const ch = String.fromCharCode(65+c);
    ctx.fillText(ch, pad + c*cell + cell/2, pad-18);
    ctx.fillText(ch, pad + c*cell + cell/2, pad+size+18);
  }

  // grid lines
  ctx.strokeStyle = "#2a2f3a"; ctx.lineWidth = 2;
  for(let i=0;i<=SIZE;i++){
    const y = pad + i*cell; ctx.beginPath(); ctx.moveTo(pad, y); ctx.lineTo(pad+size, y); ctx.stroke();
    const x = pad + i*cell; ctx.beginPath(); ctx.moveTo(x, pad); ctx.lineTo(x, pad+size); ctx.stroke();
  }

  // valid destinations
  for(const k of state.validDests){
    const [r,c] = k.split(",").map(Number);
    const x = pad + c*cell; const y = pad + r*cell;
    ctx.fillStyle = "rgba(110,168,254,0.18)"; ctx.fillRect(x+2,y+2,cell-4,cell-4);
    ctx.strokeStyle = "#6ea8fe"; ctx.lineWidth = 2; ctx.strokeRect(x+2,y+2,cell-4,cell-4);
  }

  // tiles
  for(let r=0;r<SIZE;r++){
    for(let c=0;c<SIZE;c++){
      const t = state.board[r][c]; if(!t) continue;
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

// Fit an image into a box (contain, preserve aspect, center)
function drawImageContain(ctx, img, dx, dy, dw, dh) {
  if (!img || !img.complete) return;
  const iw = img.naturalWidth || img.width;
  const ih = img.naturalHeight || img.height;
  if (!iw || !ih) return;

  const scale = Math.min(dw / iw, dh / ih);
  const w = Math.floor(iw * scale);
  const h = Math.floor(ih * scale);
  const x = dx + Math.floor((dw - w) / 2);
  const y = dy + Math.floor((dh - h) / 2);
  ctx.drawImage(img, x, y, w, h);
}

function drawTile(ctx, x, y, size, tile, selected=false){
  // tile card
  roundRect(ctx, x+6, y+6, size-12, size-12, 14, '#f4f5f6', '#0f1218', 2);

  // icon only — force-fit into inner box with a small margin
  const inset = Math.floor(size * 0.10);
  const boxX = x + inset, boxY = y + inset;
  const boxW = size - inset * 2, boxH = size - inset * 2;

  const img = ICONS.get(keyIcon(tile.genre, tile.color));
  drawImageContain(ctx, img, boxX, boxY, boxW, boxH);

  if(selected){
    ctx.strokeStyle = '#6ea8fe'; ctx.lineWidth = 3; ctx.strokeRect(x+4,y+4,size-8,size-8);
  }
}

// ====== Input ======
canvas.addEventListener("click", (e)=>{
  const rect = canvas.getBoundingClientRect();
  const px = (e.clientX - rect.left) * (canvas.width / rect.width);
  const py = (e.clientY - rect.top) * (canvas.height / rect.height);
  const pad = 40; const size = canvas.width - pad*2; const cell = size / SIZE;
  if(px<pad || py<pad || px>pad+size || py>pad+size) return;
  const c = Math.floor((px - pad)/cell);
  const r = Math.floor((py - pad)/cell);
  onBoardClick(r,c);
});

function onBoardClick(r,c){
  if(state.phase === "slide"){
    const t = state.board[r][c];
    if(t && !state.selected){
      state.selected = {r,c};
      computeValidDests(r,c);
    } else if(state.selected && state.validDests.has(key(r,c))){
      const {r:sr,c:sc} = state.selected;
      state.board[r][c] = state.board[sr][sc];
      state.board[sr][sc] = null;
      state.selected = null; state.validDests.clear();
      state.phase = "place"; state.selectedDisplay = null;
    } else {
      state.selected = null; state.validDests.clear();
    }
  } else if (state.phase === 'place') {
    const t = state.board[r][c];

    // If you click an existing tile (and haven't picked from display),
    // switch to slide mode and start sliding that tile.
    if (t && state.selectedDisplay == null) {
      state.phase = 'slide';
      state.selected = { r, c };
      computeValidDests(r, c);
      render();
      setStatus();
      return; // stop here; don't try to place
    }

    // (placing) click an empty cell *after* choosing a display tile
    if (state.board[r][c] === null && state.selectedDisplay != null) {
      state.board[r][c] = state.display[state.selectedDisplay];
      state.display.splice(state.selectedDisplay, 1);
      drawFromSupplyToDisplay();
      state.selectedDisplay = null;

      if (boardFull()) {
        endAndScore();
      } else {
        // next player — keep turns starting in PLACE so slide remains optional
        state.current = 1 - state.current;
        state.phase = 'place';
        state.selected = null;
        state.validDests.clear();
      }
    }
  }
  renderDisplay(); render(); setStatus();
}

function computeValidDests(r,c){
  state.validDests.clear();
  const dirs = [[1,0],[-1,0],[0,1],[0,-1]];
  for(const [dr,dc] of dirs){
    let nr=r+dr, nc=c+dc;
    while(inBounds(nr,nc) && state.board[nr][nc]===null){
      state.validDests.add(key(nr,nc));
      nr+=dr; nc+=dc;
    }
  }
}
function boardFull(){ for(let r=0;r<SIZE;r++) for(let c=0;c<SIZE;c++) if(!state.board[r][c]) return false; return true; }

// ====== Display panel ======
function renderDisplay(){
  if(!displayEl) return;
  displayEl.innerHTML = "";
  state.display.forEach((t,idx)=>{
    const div = document.createElement("div");
    div.className = "slot" + (state.selectedDisplay===idx ? " sel":"" );
    div.title = `${t.genre} — ${t.color}`;
    const canv = document.createElement("canvas"); canv.width=120; canv.height=90;
    const c2 = canv.getContext("2d");
    drawTile(c2, 10, 5, 100, t, false);
    div.appendChild(canv);

    div.onclick = ()=>{
      // Selecting a display tile always allows placing (skip slide if we were in 'slide')
      state.selected = null;
      state.validDests.clear();
      state.phase = 'place';
      state.selectedDisplay = idx;
      renderDisplay();
      setStatus();
    };
    displayEl.appendChild(div);
  });
  updateSupplyBadge();
}
function updateSupplyBadge(){ if(supplyBadge) supplyBadge.textContent = `Supply: ${state.supply.length}`; }

// ====== Status & Scoring ======
function setStatus(){
  const p = state.current+1;
  const focus = state.focuses[state.current]==="genre" ? "Toys" : "Colors";
  const phase = state.phase==="slide" ? "Slide a toy" : (state.selectedDisplay==null ? "Place: select a display tile" : "Place: click an empty cell");
  const el = document.getElementById("status");
  if (el) el.textContent = `Player ${p} · Focus: ${focus} · ${phase}`;
}

function computeScoreFor(playerIdx){
  const focus = state.focuses[playerIdx];
  const seen = Array.from({length:SIZE},()=>Array(SIZE).fill(false));
  let total = 0; const groups = [];
  for(let r=0;r<SIZE;r++){
    for(let c=0;c<SIZE;c++){
      const t = state.board[r][c];
      if(!t || seen[r][c]) continue;
      const attr = focus==="genre" ? t.genre : t.color;
      const q=[[r,c]]; seen[r][c]=true; let sz=0;
      while(q.length){
        const [cr,cc]=q.shift(); sz++;
        const nbs=[[1,0],[-1,0],[0,1],[0,-1]];
        for(const [dr,dc] of nbs){
          const nr=cr+dr, nc=cc+dc;
          if(!inBounds(nr,nc) || seen[nr][nc]) continue;
          const t2 = state.board[nr][nc];
          if(t2 && ((focus==='genre' && t2.genre===attr) || (focus==='color' && t2.color===attr))){
            seen[nr][nc]=true; q.push([nr,nc]);
          }
        }
      }
      if(sz>=2){
        const pts = SCORE_TABLE[sz] ?? (sz>6 ? 15 : 0);
        total += pts; groups.push({attr, size:sz, pts});
      }
    }
  }
  return {total, groups, focus};
}

function endAndScore(){
  const s1 = computeScoreFor(0);
  const s2 = computeScoreFor(1);
  const win = s1.total===s2.total ? "Draw!" : (s1.total>s2.total? "Player 1 wins!" : "Player 2 wins!");
  showScoreModal(s1,s2,win);
}

function showScoreModal(s1,s2,headline="Scores"){
  const box = document.getElementById("scoreContent");
  if (!box) return;
  box.innerHTML = `
    <p><b>${headline}</b></p>
    <div class="hr"></div>
    <p><b>Player 1</b> — Focus: ${s1.focus==='genre'?'Toys':'Colors'} — <b>${s1.total}</b> pts</p>
    ${renderGroupList(s1.groups)}
    <div class="hr"></div>
    <p><b>Player 2</b> — Focus: ${s2.focus==='genre'?'Toys':'Colors'} — <b>${s2.total}</b> pts</p>
    ${renderGroupList(s2.groups)}
  `;
  const dlg = document.getElementById("scoreDialog");
  if (dlg && dlg.showModal) dlg.showModal();
}
function renderGroupList(groups){
  if(!groups.length) return '<p class="hint">No scoring groups.</p>';
  return '<ul>' + groups.map(g=>`<li>${g.size} in ${g.attr} → ${g.pts} pts</li>`).join('') + '</ul>';
}

// ====== Buttons ======
const newBtn = document.getElementById("newBtn");
const rulesBtn = document.getElementById("rulesBtn");
const scoreBtn = document.getElementById("scoreBtn");
if (newBtn) newBtn.onclick = newGame;
if (rulesBtn) rulesBtn.onclick = ()=>{ const d=document.getElementById("rulesDialog"); if(d&&d.showModal) d.showModal(); };
const closeRules = document.getElementById("closeRules");
if (closeRules) closeRules.onclick = ()=>{ const d=document.getElementById("rulesDialog"); if(d&&d.close) d.close(); };
if (scoreBtn) scoreBtn.onclick = ()=>{ const s1=computeScoreFor(0), s2=computeScoreFor(1); showScoreModal(s1,s2,"Current Board"); };
const closeScore = document.getElementById("closeScore");
if (closeScore) closeScore.onclick = ()=>{ const d=document.getElementById("scoreDialog"); if(d&&d.close) d.close(); };

// ====== Kickoff (preload icons first) ======
preloadIcons().then(newGame);
