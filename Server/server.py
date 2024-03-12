from sqlalchemy import create_engine
from sqlalchemy.orm import scoped_session, sessionmaker
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import Column, Integer, String

USER = "sne0103"
PW = "pa3database"
URL = "database-1.c7fzirbe8h4g.ap-northeast-2.rds.amazonaws.com"
PORT = "5432"
DB = "postgres"
engine = create_engine("postgresql://{}:{}@{}:{}/{}".format(USER, PW, URL,PORT, DB))
db_session = scoped_session(sessionmaker(autocommit=False, autoflush=False, bind=engine))

"""
Base = declarative_base()
Base.query = db_session.query_property()
Base.metadata.create_all(bind=engine)
"""

Base = declarative_base()
Base.query = db_session.query_property()

class User(Base):
    __tablename__ = 'users'
    id = Column(Integer, primary_key=True)
    name = Column(String(50), unique=True)
    passwd = Column(String(120), unique=False)
    def __init__(self, name=None, passwd=None):
        self.name = name
        self.passwd = passwd
    def __repr__(self):
        return f'<User {self.name!r}>'

class Record(Base):
    __tablename__ = 'records'
    id = Column(Integer, primary_key=True)
    name = Column(String(50), unique=False)
    score = Column(Integer, unique=False)
    date = Column(String(120), unique=False)
    def __init__(self, name=None, score=None, date=None):
        self.name = name
        self.score = score
        self.date = date
    def __repr__(self):
        return f'<User {self.name!r}>'

class MaxRecord(Base):
    __tablename__ = 'max_records'
    id = Column(Integer, primary_key=True)
    name = Column(String(50), unique=True)
    score = Column(Integer, unique=False)
    def __init__(self, name=None, score=None):
        self.name = name
        self.score = score
    def __repr__(self):
        return f'<User {self.name!r}>'

# Base.metadata.drop_all(bind=engine)
Base.metadata.create_all(bind=engine)

from flask import Flask
from flask import request
from flask import jsonify
from werkzeug.serving import WSGIRequestHandler
import json

import json
WSGIRequestHandler.protocol_version = "HTTP/1.1"

app = Flask(__name__)

@app.route("/adduser", methods=['POST'])
def add_user():
    content = request.get_json(silent=True)
    name = content["name"]
    passwd = content["passwd"]
    if db_session.query(User).filter_by(name=name).first() is None:
        u = User(name=name, passwd=passwd)
        db_session.add(u)
        db_session.commit()
        return jsonify(success=True)
    else:
        return jsonify(success=False)

@app.route("/login", methods=['POST'])
def login():
    content = request.get_json(silent=True)
    name = content["name"]
    passwd = content["passwd"]
    check = False
    result = db_session.query(User).all()
    for i in result:
        if i.name == name and i.passwd == passwd:
            check = True
    return jsonify(success=check)

@app.route("/post/records", methods=['POST'])
def post_records():
    content = request.get_json(silent=True)
    name = content["name"]
    score = int(content["score"])
    date = content["date"]
    db_session.add(Record(name=name, score=score, date=date))
    
    max_record = db_session.query(MaxRecord).filter_by(name=name).first()
    if max_record is None:
        db_session.add(MaxRecord(name=name, score=score))
    else:
        if score > max_record.score:
            db_session.query(MaxRecord).filter_by(name=name).update({"score":score})

    db_session.commit()
        
    return jsonify(success=True)

@app.route("/get/records", methods=['GET'])
def get_records():
    name = request.args.get('name')
    records = db_session.query(Record).filter_by(name=name).order_by(Record.score.desc())
    json_list = []
    for i in records:
        data = {}
        data["score"] = i.score
        data["date"] = i.date
        json_list.append(data)
    return jsonify(json_list)

@app.route("/get/all_records", methods=['GET'])
def get_all_records():
    records = db_session.query(MaxRecord).order_by(MaxRecord.score.desc())
    json_list = []
    for i in records:
        print("Good")
        data = {}
        data["name"] = i.name
        data["score"] = i.score
        json_list.append(data)
    return jsonify(json_list)


#db clearing functions(only for test)
@app.route("/delete_tables", methods=['GET'])
def delete_tables():
    db_session.query(User).delete()
    db_session.query(Record).delete()
    db_session.query(MaxRecord).delete()
    db_session.commit()
    return jsonify(success=True)

if __name__ == "__main__":
    app.run(host='localhost', port=8888)

