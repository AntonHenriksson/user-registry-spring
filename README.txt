
In local profile --dev this docker-compose starts a mariadb database together with the spring app.

To run in production change the .env variables to prod.

To run locally with docker-compose:

docker-compose --profile dev up

To close the network when you're done:

docker-compose --profile dev down --volumes --remove-orphans