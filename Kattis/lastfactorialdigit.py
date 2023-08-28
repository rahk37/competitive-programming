# Last Factorial Digit - Kattis
# Code written in Python3 by Riley Wikel

N = int(input())
nums = []
for x in range(N):
    nums.append(int(input()))

output_arr = []
for num in nums:
    output = 1
    for x in range(1, num+1):
        output = x * output
    output_arr.append(output % 10)

for num in output_arr:
    print(num)
