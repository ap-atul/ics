## AES 
Simple Advanced Encryption Standard  - using 16 bit key and 16 bit data

For more [info] (https://sandilands.info/crypto/AdvancedEncryptionStandard.html)

## Simplified Version 

* Input: 16-bit block of plaintext; 16-bit key
* Output: 16-bit block of ciphertext
* Four operations:
	* Add Key: XOR of a 16-bit key and 16-bit state matrix
	* Nibble substitution: table lookup that swaps nibbles (4-bits)
	* Shift Row: shift of nibbles in a row
	* Mix Column: re-order columns
* 3 rounds:
	* Round 0: Add key
	* Round 1: All four operations
	* Round 2: Three operations
