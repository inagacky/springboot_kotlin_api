#!/bin/bash

SCRIPT_DIR=$(cd $(dirname $0) && pwd)


docker-compose -f docker-compose.yml stop || exit
