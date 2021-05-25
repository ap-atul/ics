from keygen import keygen
import socket

pub_key, priv_key, n = keygen()

s = socket.socket()
s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
s.bind(('', 8000))
s.listen(1)
print("Server listening on localhost at port 8000")
try:
    while True:
        conn, addr = s.accept()
        print("Got connection from client {}".format(str(addr)))
        msg = str(n) + "DELIM" + str(pub_key)
        conn.send(str(msg).encode())
        # Get the encrypted message from the server
        code = int(str(conn.recv(100).decode()))
        print("Received encrypted message: {}".format(code))
        # Decrypt it using private key
        msg = pow(code, priv_key, n)
        print("Original message: {}".format(msg))
        # Close the connection
        conn.close()
except KeyboardInterrupt:
    s.close()
