# Using GitHub Actions for browser build

These instructions allow you to build AndroidAPS with a browser.

## Setup Github AndroidAPS repository

1. Fork https://github.com/nightscout/AndroidAPS into your account. If you already have a fork of AndroidAPS in GitHub, you can't make another one. You can continue to work with your existing fork, or delete that from GitHub and then fork https://github.com/nightscout/AndroidAPS.

## Prepare your existing or new Android Studio signing keystore

1. Base64 encoding of the keystore file: certutil keystore.jks SIGNING_KEY.txt

## Setup Github secrets for the keystore and the GPG encryption

1. In the forked AndroidAPS repo, go to Settings -> Secrets and variables -> Actions.
1. For each of the following secrets, tap on "New repository secret", then add the name of the secret, along with the value you defined during keystore creation time. As value for the secret SIGNING_KEY use the text out of the file SIGNING_KEY.txt, which is stored between the two lines --BEGIN CERTIFICATE-- and --END CERTIFICATE--.  
    * `KEY_STORE_PASSWORD`
    * `KEY_ALIAS`
    * `KEY_PASSWORD`
    * `SIGNING_KEY`
    * `GPG_PASSPHRASE`
