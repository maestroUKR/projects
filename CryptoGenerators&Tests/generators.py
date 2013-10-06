from mpmath.libmp.backend import gmpy

__author__ = 'eugene'

import random as prng
import stringToBits as toBits
import lab3 as genGiffie
from gmpy import mpz
import time

LENGTH = 1024*1024*2

class StandardPythonPRNG():
	"""
	Generate prn using built-in
	Python prng from 'random' library

	prn: str
	return: prn
	"""

	def __init__(self):
		self.prn = ""

	def generate(self):
		temp = ""
		for i in xrange(LENGTH):
			var = prng.randint(0,1)
			temp += str(var)
		self.prn = temp
		return self.prn


class LSR_20():
	"""
	Generate prn by LSR method
	and x[i] = (x[i-3] + x[i-5] + x[i-9] + x[i-20])mod2

	prn: string
	return: prn
	"""

	def __init__(self):
		self.prn = ""

	def generate(self):
		temp = ""
		for i in xrange(21):
			temp += str(prng.randint(0, 1))

		for i in xrange(21, LENGTH):
			temp += str((int(temp[i - 3]) + int(temp[i - 5]) +\
			                 int(temp[i - 9]) + int(temp[i - 20])) % 2)
		self.prn = temp
		return self.prn



class LSR_89():
	"""
	Generate prn by LSR method
	and x[i] = (x[i-38] + x[i-89])mod2

	prn: string
	return: prn
	"""

	def __init__(self):
		self.prn = ""

	def generate(self):
		temp = ""
		for i in xrange(90):
			temp += str(prng.randint(0, 1))
		for i in xrange(90, LENGTH):
			temp += str((int(temp[i - 38]) + int(temp[i - 89])) % 2)
		self.prn = temp
		return self.prn

class PRNG_fromText():
	"""
	Generate prn from text

	prn: string
	return: prn
	"""

	def __init__(self):
		self.prn = ""

	def generate(self):
		file = open("text.txt", 'rb')
		text = file.read();
		file.close()
		self.prn = toBits.display_bits(toBits.string_to_bits(text))
		return self.prn


class BBS():
	"""
	Generate prn by BBS method

	prn: string
	return: prn
	"""

	def __init__(self):
		self.p = mpz("DD4ABECC719B945481602AD72BA43B7E5998C07EB", 16)
		self.q = mpz("6C5CF455F6FEE7B8DE5D2D041E80821A207F440253", 16)
		self.prn = ""

	def generate(self):
		n = self.p * self.q
		r0 = prng.randint(0, n)
		self.prn += str(r0 % 2)
		k = r0
		temp = ""
		for i in xrange(LENGTH):
			r = pow(k, 2, n)
			x = r % 2
			k = r
			temp += str(x)
		self.prn = temp
		return self.prn

if __name__ == '__main__':
	t1 = time.time()
	p = StandardPythonPRNG()
	num = p.generate()
	print("Runtime = " + str(time.time() - t1))

	t2 = time.time()
	p = LSR_20()
	num = p.generate()
	print("Runtime = " + str(time.time() - t2))

	t3 = time.time()
	p = LSR_89()
	num = p.generate()
	print("Runtime = " + str(time.time() - t3))

	t5 = time.time()
	p = BBS()
	num = p.generate()
	print("Runtime = " + str(time.time() - t5))