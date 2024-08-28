from enum import Enum

class Bit(int,Enum):
    ZERO = 48
    ONE = 49
    NULL = 0

class BitStream:
    def __init__(self, bits : list[Bit] | str = []):
        if isinstance(bits,list):
            self.__stream__ = bytearray(bits)
        else:
            bits = [b for c in bits for b in Bit if c == str(chr(b.value))]
            self.__stream__ = bytearray(bits)

    def __iter__(self):
        return iter(self.__stream__)
    
    def __len__(self):
        return len(self.__stream__)
    
    def __repr__(self):
        return ''.join(str(b) for b in self.__stream__.decode())
    
    def __getitem__(self, index : int):
        return self.__stream__.__getitem__(index)
    
    def __setitem__(self, index : int, value : Bit):
        self.__stream__.__setitem__(index,value)

    def add(self, bit : Bit = Bit.ZERO):
        self.__stream__.append(bit)

    def rem(self, index : int):
        self.__stream__.pop(index)

class HammingBitStream(BitStream):
    def __init__(self, bits : list[Bit] | str = []):
        super().__init__(bits)

    def __repr__(self):
        return ''.join(['_' if b == Bit.NULL.value else chr(b) for b in self.__stream__])

    def decode(self):
        return 0

class Hamming:
    def __init__(self):
        pass

    def encode(self, bits : BitStream):
        controlbits = [2**i for i in range(0,len(bits)//2,1)]
        encoded = HammingBitStream()

        i,bit = 1,0
        while bit < len(bits):
            if i in controlbits:
                encoded.add(Bit.NULL)
            else:
                encoded.add(bits[bit])
                bit+=1
            i+=1

        for n, bit in enumerate(encoded, 1):
            if bit == Bit.NULL:
                s = 0
                for c in [j for i in range(n,len(encoded)+1,n*2) for j in range(i,i+n)]:
                    if c <= len(encoded) and encoded[c-1] == Bit.ONE:
                        s+=1
                encoded[n-1] = Bit.ZERO if s % 2 == 0 else Bit.ONE
        return encoded

    def decode(self, bits : HammingBitStream):
        controlbits = [2**i for i in range(0,len(bits)//2,1)]
        decoded = BitStream([bit for n, bit in enumerate(bits, 1) if not n in controlbits])
        return decoded

    def check(self, bits : HammingBitStream):
        controlbits = [2**i for i in range(0,len(bits)//2,1)]
        error_bit = 0

        for n, bit in enumerate(bits,1):
            if n in controlbits:
                s = 0
                controlled = [j for i in range(n,len(bits)+1,n*2) for j in range(i,i+n)]
                for c in controlled[1:]:
                    if c <= len(bits) and bits[c-1] == Bit.ONE:
                        s+=1

                if not int(chr(bit)) == int(s % 2):
                    error_bit+=n
        
        bits[error_bit-1] = Bit.ONE if bits[error_bit-1] == Bit.ZERO else Bit.ZERO
        return bits

hamming = Hamming()

message = BitStream('01010101011')
encoded = hamming.encode(message) #100110100101011
print('encoded message: ', encoded)

encoded_error = HammingBitStream('100110101101011')
print('encoded error: ', encoded_error)

correct = hamming.check(encoded_error)
print('corrected: ',correct)
