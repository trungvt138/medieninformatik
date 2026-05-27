def treppe(stairs, n=2, path=None, result=None):
    if path is None:
        path = []
    if result is None:
        result = []

    if stairs == 0:
        result.append(path[:])
        return result

    for step in range(1, n + 1):
        if step <= stairs:
            path.append(step)
            treppe(stairs - step, n, path, result)
            path.pop()

    return result


if __name__ == "__main__":
    paths = treppe(100)
    print(f"{len(paths)} ways: \n")
    for path in paths:
        print(f"{path}\n")
    
