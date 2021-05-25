import socket
from random import getrandbits

s = socket.socket()
s.connect(('', 8000))
print("Connected to server localhost at port 8000")

server_keys = str(s.recv(100).decode()).split("DELIM", 2)
n = int(server_keys[0])
pub_key = int(server_keys[1])

msg = getrandbits(48)
print("Original message: {}".format(msg))
# Encrypt it using public key and public value
code = pow(msg, pub_key, n)
print("Encrypted message: {}".format(code))
# Send the encrypted message to server
s.send(str(code).encode())
# Close the connection
s.close()
