import math

en_alphabet = "abcdefghijklmnopqrstuvwxyz"
en_uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
# Store statistic for H1 entropy
one_dict = {}
# Store statistic for H2 entropy
double_dict = {}
one_entropy = 0.0
double_entropy = 0.0

'''
Read input file and write data in
another file with changing letters 
to lowercase.
'''
in_file = open('TEXT.txt', 'r')
out_file = open('new_text.txt', 'w')
for char in in_file.read():
    if char in en_alphabet:
        out_file.write(char)
    elif char in en_uppercase:
        item = en_uppercase.find(char)
        out_file.write(en_alphabet[item])
in_file.close()
out_file.close()

'''
Count number of every letter 
in file and write this statistic.
'''
work_file = open('new_text.txt', 'r')
for char in work_file.read():
    if char not in one_dict:
        one_dict[char] = 1.0
    elif char in one_dict:
        one_dict[char] +=1.0
work_file.close()

'''
Compute probability for
every letter in file.
'''
all_item = 0.0
for item in one_dict:
    all_item += one_dict[item]
print "Probabilities of every letters:" 
for item in one_dict:
    print str(item) + " " + str(one_dict[item]) + "/" + str(all_item)

'''
Write data from file
to the buffer
'''
string_let = ""
work_file = open('new_text.txt', 'r')
for item in work_file.read():
    string_let += item
'''
Search bigrams and store statistic 
for H2 entropy
'''
count = 0
while count+1 < len(string_let):
    str_ = string_let[count]
    str_ += string_let[count+1]
    if str_ not in double_dict:
        double_dict[str_] = 1.0
        count += 2
    elif str_ in double_dict:
        double_dict[str_] += 1.0
        count +=2

'''
Compute probability for
every bigram in file.
'''
all_double_item = 0.0
for item in double_dict:
    all_double_item += double_dict[item]
print "Probabilities of double letters:"
for item in double_dict:
    print str(item) + " " + str(double_dict[item]) + "/" + str(all_double_item)


'''
Calculate entropy H1 and H2
'''
print "Calculating entropy ..."
for item in one_dict:
    one_entropy += (one_dict[item]/all_item)*math.log(one_dict[item]/all_item, 2)
print "Entropy H1 = " + str(abs(one_entropy))

for item in double_dict:
    double_entropy += (double_dict[item]/all_double_item)*math.log(double_dict[item]/all_double_item, 2)
print "Entropy H2 = " + str(abs(double_entropy/2))
