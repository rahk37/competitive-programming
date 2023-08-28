N = int(input())

num_line = input()
nums = num_line.split()

sum = 0
for num in nums:
    sum+=int(num)

print(sum)
