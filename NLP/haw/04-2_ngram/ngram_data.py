import re
from pathlib import Path


FollowerTable = dict[tuple[str, ...], dict[str, int]]


def tokenize(text: str) -> list[str]:
    return re.findall(r'[a-z]+', text.lower())


def load_sentences(folder: Path) -> list[list[str]]:
    """Read the review files in filename order and return one token list per sentence."""
    sentences = []
    for path in sorted(folder.glob('*/*.txt'), key=lambda path: path.name):
        text = path.read_text(encoding='utf-8')
        for sentence in re.split(r'[.!?]+', text):
            words = tokenize(sentence)
            if words:
                sentences.append(words)
    return sentences


def word_events(sentence: list[str], n: int) -> list[tuple[tuple[str, ...], str]]:
    """Pair each target, including the sentence end, with its n - 1 preceding tokens."""
    if n < 1:
        raise ValueError('n must be positive')
    tokens = ['<start>'] * (n - 1) + sentence + ['<end>']
    events = []
    for i in range(n - 1, len(tokens)):
        context = tuple(tokens[i - n + 1:i])
        events.append((context, tokens[i]))
    return events


def count_followers(sentences: list[list[str]], n: int) -> FollowerTable:
    """Count the observed next words separately for each context."""
    counts = {}
    for sentence in sentences:
        for context, word in word_events(sentence, n):
            if context not in counts:
                counts[context] = {}
            followers = counts[context]
            followers[word] = followers.get(word, 0) + 1
    return counts


def next_word_counts(models: dict[int, FollowerTable], words: list[str], n: int) -> dict[str, int]:
    """Use the longest available context, down to the overall unigram counts."""
    if n not in models:
        raise ValueError('Choose a trained model order')
    history = ['<start>', '<start>'] + words[-2:]
    if n == 3:
        pair = tuple(history[-2:])
        if pair in models[3]:
            return models[3][pair]
    if n >= 2:
        last_word = (history[-1],)
        if last_word in models[2]:
            return models[2][last_word]
    return models[1][()]
