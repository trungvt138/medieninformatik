import csv
from pathlib import Path

ERROR_TYPES = ('spelling', 'format', 'extra_word', 'advertising')
Finding = tuple[str, int, str]


def read_findings(path: str | Path) -> set[Finding]:
    findings = set()
    # This reader also accepts student CSVs saved by Excel with a UTF-8 byte order mark.
    with Path(path).open(encoding='utf-8-sig', newline='') as stream:
        reader = csv.DictReader(stream)
        missing = {'id', 'line', 'type'}.difference(reader.fieldnames or ())
        if missing:
            raise ValueError(f'{path}: missing columns {sorted(missing)}')
        for row in reader:
            document_id = row['id']
            line = row['line'] or ''
            kind = row['type']
            location = f'{path}, CSV line {reader.line_num}'
            if not document_id or not document_id.strip():
                raise ValueError(f'{location}: missing document id')
            if not line.isascii() or not line.isdecimal() or int(line) < 1:
                raise ValueError(f'{location}: expected a positive line number, got {line!r}')
            if kind not in ERROR_TYPES:
                raise ValueError(f'{location}: unknown type {kind!r}; expected one of {ERROR_TYPES}')
            findings.add((document_id, int(line), kind))
    return findings


def compare_findings(predictions: str | Path, reference: str | Path) -> dict[str, set[Finding]]:
    reported = read_findings(predictions)
    expected = read_findings(reference)
    return {'TP': reported & expected, 'FP': reported - expected, 'FN': expected - reported}


def ratio(numerator: int, denominator: int) -> float:
    if denominator == 0:
        return 0.0
    return numerator / denominator


def evaluate(predictions: str | Path, reference: str | Path) -> list[dict[str, str | int | float]]:
    '''Compare the two CSVs by document ID, line number and anomaly type.'''
    cases = compare_findings(predictions, reference)
    rows = []
    for kind in (*ERROR_TYPES, 'overall'):
        counts = {}
        for label, findings in cases.items():
            counts[label] = sum(kind == 'overall' or finding[2] == kind for finding in findings)
        tp, fp, fn = counts['TP'], counts['FP'], counts['FN']
        rows.append({
            'type': kind, **counts,
            'precision': ratio(tp, tp + fp),
            'recall': ratio(tp, tp + fn),
            'F1': ratio(2 * tp, 2 * tp + fp + fn),
        })
    return rows
