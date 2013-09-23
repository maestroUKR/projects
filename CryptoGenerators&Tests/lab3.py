from math import log

# --- Подсчитывает новый бит и сдвигает биты ------
def Shift(S, f):
	newbit = sum([S[i] for i in f]) % 2
	S = S[1:] + [newbit]
	return S
	
# --- Генерирует последовательность ------	
def L(S, f, T):
	x = [0]
	
	for i in range(T):
		S = Shift(S, f)
		x += [S[0]]
		
	return x
	
# --- Генератор Геффе -----	
def Geffe(x,y,s, N):
	z = [(s[i]*x[i] + (1 + s[i])*y[i]) % 2 for i in range(N)]
	return z

# --- Подсчет статистики совпадений x,z -----	
def getR(x,z, N):
	result = sum([(x[i] + z[i]) % 2 for i in range(N)])
	return result
	
# --- Преобразует число в двоичное заполнение региста -----	
def generate(i, N):
	string = bin(i)[2:]
	x = [0]*(N - len(string)) + [int(i) for i in string]
	return x
	

# --- Ищем начальное заполнение региста L₁, L₂ -----	
def findkey(n,f, z, C, N):
	T = 2**n
	variants = {}
	
	for i in range(T):
		key = generate(i, n)
		x = L(key, f, N)
		R = getR(x,z, N)
		
#		print "".join(map(str, key)), "\t", "".join(map(str, x)), "\t", R, "--!" if (R < C) else ""
		
		if (R < C): variants[R] = key

#	print variants
	
	return variants[min(variants)]

# --- Отбраковка неверных заполнений L₃ -----	
def checkL3(x,y,s,z, N):	
	for i in range(N):
		if (x[i] != y[i]):
			if not (((s[i] == 1) and (z[i] == x[i])) or ((s[i] == 0) and (z[i] == y[i]))):
				return False
		
	return True

# --- Ищем начальное заполнение региста L₃ -----	
def findkey3(n,f, x,y,z, N):
	T = 2**n
	
	for i in range(T):
		key = generate(i, n)
		s = L(key, f, N)
		if checkL3(x,y,s,z, N): return key
	
	return None
	
# --- Ищем квантиль нормального распредиления -----		
def getQuantile(alpha):
	res, p, t = 0, 0, 0
	
	p = alpha if (alpha < 0.5) else 1 - alpha
		
	t = (-2 * log(p))**0.5
	res = t - ((0.010328 * t + 0.802853) * t + 2.515517) / (((0.001308 * t + 0.189269) * t + 1.432788) * t + 1)
	
	return res

# --- Получаем порог C и необходимую длину последовательности N -----	
def getNC(ta, tb, p1, p2):
	N = (ta*(p1*(1-p1))**0.5 + tb*(p2*(1-p2))**0.5)**2 / (p1 - p2)**2
	C = N*p1 + ta*(N*p1*(1 - p1))**0.5
	return (N,C)

# ------------------------------------------------------------------------------
def GenerateGiffie():
	z = "01011101000101001000000010100000010001100011111111011110010001011000111000100101100111100011010001100101110001101100110010110110100010001101000101000010110101100001110101111011110000010001101111000110111100100101101100001011010011110010101111111100100100111100000000011110100000000011011011111101000010101011000110001110111011001011100100011000100110111111000101101000000111100001010011100011010110100011101111100001100011011000010011101100101000011111110010000001000111010000100000111001000101111101"
	z = [int(i) for i in z]
	
#	print "z =", "".join(map(str, z[:60])), "..."
	
	n1, f1 = 11, [2, 0]				# L₁: x¹¹ + x² + 1
	n2, f2 = 9,  [4, 3, 1, 0]		# L₂: x⁹ + x⁴ + x³ + x + 1
	n3, f3 = 10, [3, 0]				# L₃: x¹⁰ + x³ + 1
	
	b1 = 2**(-1*n1)
	b2 = 2**(-1*n2)
#	print "ß₁ =", b1
#	print "ß₂ =", b2
	
	ta = 2.3
	tb1 = getQuantile(b1)
	tb2 = getQuantile(b2)
	p1, p2 = 0.25, 0.5
#	print "tß₁ =", tb1
#	print "tß₂ =", tb2
	
	N1, C1 = getNC(ta, tb1, p1, p2)
	N2, C2 = getNC(ta, tb2, p1, p2)
#	print "N₁ =", N1, "\tC₁ =", C1
#	print "N₂ =", N2, "\tC₂ =", C2
#	print
	
	N1, C1 = int(N1), int(C1)
	N2, C2 = int(N2), int(C2)
	
#	print "Find k1:"
	k1 = findkey(n1, f1, z, C1, N1)
#	print
#	print "Find k1:"
	k2 = findkey(n2, f2, z, C2, N2)
	
#	N = min(N1, N2)
	N = 1024
	x = L(k1, f1, N)
#	y = L(k2, f2, N)
	y = L(k1, f2, N)

	k3 = findkey3(n3, f3, x,y,z, N)
	
#	print "k₁ =", k1
#	print "k₂ =", k2
#	print "k₃ =", k3
	
#	s = L(k3, f3, N)
	s = L(k1, f3, N)
#	print "x =", "".join(map(str, x))
#	print "y =", "".join(map(str, y))
#	print "s =", "".join(map(str, s))
#	print
	
	z_test = Geffe(x,y,s, N)
#	print "z'=", "".join(map(str, z_test))
#	print "z =", "".join(map(str, z[:N]))
#	print "z' == z:", z_test == z[:N]
	return z_test

