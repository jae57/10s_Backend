kill $(ps aux | grep 'flask'| awk '{print $2}')
export FLASK_APP=controller.py
nohup flask run --host=0.0.0.0 > 10s.log & 