import random 
str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()?"

passLen = 12
password = "".join(random.sample(str,passLen))
print (password)
