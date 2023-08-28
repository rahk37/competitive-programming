line = input()
a, b = line.split()
a = int(a)
b = int(b)

if a < b:
    print(str(a) + " " + str(b))
else:
    print(str(b) + " " + str(a))
