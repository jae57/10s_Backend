kill $(ps aux | grep 'gunicorn'| awk '{print $2}')
mv 10s.log 10s.log.$(date +%Y%m%d%H%M%S)
nohup gunicorn -b 0.0.0.0:5000 "backend10s:create_app()" > 10s.log &
