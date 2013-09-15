import os
from Crypto.Hash import SHA256

# input file
infile = "your input file"
# reverse file
outfile = "your output file"


def reverse_file(in_filename, fout, blocksize=1024):
	'''
	Function to return file in reverse order.

	in_filename: input file, path
	fout: out file, path
	blocksize: default, size in bytes

	returns: none
	'''
    filesize = os.path.getsize(in_filename)
    fin = open(in_filename, 'rb')
    fout = open(fout, 'wb')
    for i in range(filesize // blocksize, -1, -1):
        fin.seek(i * blocksize)
        data = fin.read(blocksize)
        fout.write(data[::-1])

def get_file_check(filename):
	'''
	Compute hash of file h0

	filename: input file(in reverse order), path

	returns: hash h0
	'''
	reverse_file(filename, outfile)
	size_file = os.path.getsize(outfile)
	first_block = size_file-(size_file/1024)*1024

	#hashes file by blocks 
    chunk_size = 1024
    with open(filename, 'rb') as f:
        chunk = f.read(first_block)
        reversedChunk = chunk[::-1]
        h = SHA256.new(reversedChunk)
        while True:
            chunk = f.read(chunk_size)
            if len(chunk) == 0:
                break
            reversedChunk = chunk[::-1]
            h = SHA256.new(reversedChunk + h.hexdigest().decode("hex"))
    return h.hexdigest()

get_file_check(infile)

