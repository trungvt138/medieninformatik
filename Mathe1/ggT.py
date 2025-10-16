def gcd2(a, b):
    a, b = abs(a), abs(b)
    while b:
        a, b = b, a % b
    return a

def ggT(*nums):
    if not nums:
        return None  # or raise an error
    result = nums[0]
    for n in nums[1:]:
        result = gcd2(result, n)
    return result

print(ggT(142766, 391989, 430882))  # 19
