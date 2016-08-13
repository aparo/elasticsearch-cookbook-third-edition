curl  -XPUT 'http://127.0.0.1:9200/lmyogs-000001' -d '
{
  "aliases": {
    "logs_write": {}
  }
}'