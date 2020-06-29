
def exp(a, n):
    if n == 0:  # base case
        return 1

    if n%2 == 0:  # even numbers
        return exp(a*a, int(n/2))
    else:  # odd numbers
        return a * exp(a*a, int(n/2))


for i in range(0,20):
    print(f"exp(2,{i}) = {exp(2,i)}")


# for this i'm using two pointers for each of the lists.
# I iterate through both of them, checking to see if the
# current elements match. If they do, we add that value
# to the results. If they don't, then we increment the pointer
# for the smaller side of the list. This continues n times, or
# until we've gone through one of the lists entirely.

a = [1, 2, 3, 4]
b = [3, 4, 5, 10]
c = []

i = 0
j = 0
n = len(a) + len(b)

for k in range(0, n):
    if i >= len(a) or j >= len(b):
        break
    elif a[i] == b[j]:
        c.append(a[i])
        i += 1
        j += 1
    elif a[i] < b[j]:
        i += 1
    else:
        j += 1

print(c)
