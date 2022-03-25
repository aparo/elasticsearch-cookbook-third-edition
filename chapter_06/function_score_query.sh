curl -XPOST 'localhost:9200/test-index/test-type/_search?pretty' -d '{
    "query": {
        "function_score": {
            "query": {
                "query_string": {
                    "query": "bill"
                }
            },
            "functions": [{
                "linear": {
                    "position": {
                        "origin": "0",
                        "scale": "20"
                    }
                }
            }],
            "score_mode": "multiply"
        }
    }
}'

