from flask import Flask, jsonify, request
from flask_cors import CORS
from bd import *

app = Flask(__name__)
CORS(app, resources={
    r"/api/*": {
        "origins": "*",
        "methods": ["GET", "POST", "OPTIONS"],
        "allow_headers": ["Content-Type"]
    }
})

@app.route('/api/send', methods=['POST'])  # Remova 'OPTIONS' aqui!
def post_data():
    try:
        data = request.json
        add_user(data['name'], data['email'], data['password'])
        return jsonify({"message": f"Olá, {data['name']}!"})
    
    except ExistsEmailException as e:
        return jsonify({"error": str(e)}), 409
    except PasswordException as e:
        return jsonify({"error": str(e)}), 400
    except NameException as e:
      return jsonify({"error": str(e)}), 400
    except InvalidEmailException as e:
      return jsonify({"erro": str(e)}), 400

if __name__ == '__main__':
    app.run(debug=False)