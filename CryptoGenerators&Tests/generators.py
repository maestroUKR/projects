from mpmath.libmp.backend import gmpy

__author__ = 'eugene'

import random as prng
import stringToBits as toBits
import lab3 as genGiffie
from gmpy import mpz


class Generator():
	def __init__(self):
		self.prn = 0

	def generate(self):
		print

	def display(self):
		print

	def getPRN(self):
		return self.prn

	def getLength(self):
#		return 1024
		return 1024*1024

class StandartPythonPRNG(Generator):
	"""
	Generate prn using built-in
	Python prng from 'random' library

	prn: str
	return: prn
	"""

	def __init__(self):
		self.prn = ""

	def generate(self):
		for i in range(Generator.getLength(self)):
			self.prn += str(prng.randint(0,1))

	def display(self):
		print self.prn
		print "length: " + str(len(self.prn))

	def getPRN(self):
		return self.prn


class LSR_20(Generator):
	"""
	Generate prn by LSR method
	and x[i] = (x[i-3] + x[i-5] + x[i-9] + x[i-20])mod2

	prn: string
	return: prn
	"""

	def __init__(self):
		self.prn = ""
		for i in range(21):
			self.prn += str(prng.randint(0, 1))

	def generate(self):
		for i in range(21, Generator.getLength(self)):
			self.prn += str((int(self.prn[i - 3]) + int(self.prn[i - 5]) +\
			                 int(self.prn[i - 9]) + int(self.prn[i - 20])) % 2)

	def display(self):
		print self.prn
		print "length: " + str(len(self.prn))

	def getPRN(self):
		return self.prn


class LSR_89(Generator):
	"""
	Generate prn by LSR method
	and x[i] = (x[i-38] + x[i-89])mod2

	prn: string
	return: prn
	"""

	def __init__(self):
		self.prn = ""
		for i in range(90):
			self.prn += str(prng.randint(0, 1))

	def generate(self):
		for i in range(90, Generator.getLength(self)):
			self.prn += str((int(self.prn[i - 38]) + int(self.prn[i - 89])) % 2)

	def display(self):
		print self.prn
		print "length: " + str(len(self.prn))

	def getPRN(self):
		return self.prn


class PRNG_fromText(Generator):
	"""
	Generate prn from text

	prn: string
	return: prn
	"""

	def __init__(self):
		self.prn = ""

	def generate(self):
		self.prn = toBits.display_bits(toBits.string_to_bits(toBits.text))

	def display(self):
		print self.prn
		print "length: " + str(len(self.prn))

	def getPRN(self):
		return self.prn


class BBS(Generator):
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
		for i in range(1, Generator.getLength(self)):
			r = pow(k, 2, n)
			x = r % 2
			k = r
			self.prn += str(x)
			print "Generate ", i

	def display(self):
		print self.prn
		print "length: " + str(len(self.prn))

	def getPRN(self):
		return self.prn


class Giffie(Generator):
	"""
	Generate prn by LSR method
	called Giffie

	prn: string
	return: prn
	"""

	def __init__(self):
		self.prn = ""

	def generate(self):
		self.prn = "".join(map(str, genGiffie.GenerateGiffie()))

	def display(self):
		print "".join(map(str, self.prn))
		print "length: " + str(len(self.prn))

	def getPRN(self):
		return self.prn

if __name__ == '__main__':
#	prn = [StandartPythonPRNG(), LSR_20(), LSR_89(), PRNG_fromText(), BBS(), Giffie()]
	prn = BBS()
#	for item in prn:
#		print item
#		item.generate()
#		item.display()
	prn.generate()
	prn.display()

