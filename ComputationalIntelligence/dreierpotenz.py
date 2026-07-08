x = 59049

def dreierpotenz_v1(z):
    if z < 0:
        if z == -3:
            return True
        if z % 10 == 3 or z % 10 == 7:
            if z % 9 == 0:
                if z % 7 != 0:
                    return True
    else:
        if z == 1 or z == 3:
            return True
        if z % 10 == 1 or z % 10 == 3 or z % 10 == 7 or z % 10 == 9:
            if z % 9 == 0:
                if z % 7 != 0:
                    return True
    
    return False

print(dreierpotenz_v1(x))

def dreierpotenz_v2(z):
    if z == 1 or z == 3 or z == -3:
        return True
    if z % 9 == 0:
        if z % 5 != 0 and z % 2 != 0 and z % 7 != 0:
            if z < 0:
                if z % 10 == 3 or z % 10 == 7:
                    return True
            else:
                return True
    return False

print(dreierpotenz_v2(x))

def dreierpotenz_v3(z):
    if z == 1 or z == 3:
        return True
    if z % 9 == 0 and z % 5 != 0 and z % 2 != 0 and z % 7 != 0 and x > 0:
        return True
    return False
print(dreierpotenz_v3(x))