# group13-backend

## JWT Token Setup
Create the keys folder

`mkdir src/main/resources/keys`

Change directory into the keys folder

`cd src/main/resources/keys`

Generate a prime256v1 private key
`openssl ecparam -name prime256v1 -genkey -noout -out private-key.pem`

Generate the public key from the private key
`openssl ec -in private-key.pem -pubout > public-key.pem`

Change directory back into the root project directory.
