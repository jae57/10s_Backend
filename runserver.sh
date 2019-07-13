mv 10s.log 10s.log.$(date +%Y%m%d%H%M%S) 
export FLASK_APP=controller.py
nohup flask run --host=0.0.0.0 > 10s.log & 
