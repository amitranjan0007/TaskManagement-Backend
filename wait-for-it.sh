#!/usr/bin/env bash

set -e

host="$1"
shift
cmd="$@"

echo "Waiting for MySQL at $host:3306 to be available..."

until nc -z -v -w30 $host 3306
do
  echo "MySQL is unavailable - sleeping"
  sleep 5
done

echo "MySQL is up - executing command"
exec $cmd
