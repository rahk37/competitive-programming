# Spelling Bee - Kattis
# Code written in Python3 by Riley Wikel

line = input()
letters = list(line)

n = int(input())

for x in range(n):
    dict_word = input()
    dict_letters = set(dict_word)
    
    if letters[0] in dict_letters and len(dict_word) > 3 and not dict_letters.difference(line):
        print(dict_word)
