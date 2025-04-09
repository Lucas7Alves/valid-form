import hashlib, os, re
from email_validator import validate_email, EmailNotValidError

db = {
  "users": {},
  "last_id": 0
}

def add_user(name, email, password):
  
  if email_exists(email):
    raise ExistsEmailException("Email already exists!")
  
  if not valid_email(email):
    raise InvalidEmailException("Invalid email!")
  
  if valid_name(name) == 1:
    raise NameException("Name can't less than 3 characters")
  elif valid_name(name) == 2: 
    raise NameException("Name can't more than 20 characters")
  
  if valid_password(password) == 1:
    raise PasswordException("The password can't be less than 3 characters")
  elif valid_password(password) == 2:
    raise PasswordException("The password can't be more than 20 characters")
  
  salt = os.urandom(16)
  hash_object = hashlib.sha256()

  hash_object.update(salt + password.encode('utf-8'))
  hash_code = hash_object.hexdigest()
  
  db["last_id"] +=1
  db["users"][db["last_id"]] = {
    "name": name,
    "email":email,
    "salt": salt.hex(),
    "password":hash_code
  }
  
def email_exists(target_email):
    for user_data in db["users"].values():
        if user_data["email"] == target_email:
            return True
    return False
  
def valid_email(email):
    try:
        valid = validate_email(email, check_deliverability=True)
        normalized_email = valid.email
        return True, normalized_email
    except EmailNotValidError as e:
        return False
  
def valid_name(name):
  if len(name) <= 2:
    return 1
  if len(name) >= 20:
    return 2
  return 0
def valid_password(password):
  if len(password) < 8:
    return 1
  elif len(password) > 20:
    return 2
  return 0


class InvalidEmailException(Exception):pass
class ExistsEmailException(Exception):pass

class NameException(Exception):pass

class PasswordException(Exception):pass