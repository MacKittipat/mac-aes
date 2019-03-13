var CryptoJS = require("crypto-js");


var plainText = "Hello World. This is Mac.";
var passPhrase = "jdsongkran123456";

var keySize = 128 / 32;
var iterationCount = 1000;

var iv = CryptoJS.lib.WordArray.random(128/8).toString(CryptoJS.enc.Hex);
var salt = CryptoJS.lib.WordArray.random(128/8).toString(CryptoJS.enc.Hex);

var key = CryptoJS.PBKDF2(passPhrase, CryptoJS.enc.Hex.parse(salt), { keySize: keySize, iterations: iterationCount });

var encrypted = CryptoJS.AES.encrypt(plainText, key, { iv: CryptoJS.enc.Hex.parse(iv) });

console.log("iv : " + iv);
console.log("salt : " + salt);
console.log("ciphertext : " + encrypted);
console.log("passphrase : " + passPhrase);

