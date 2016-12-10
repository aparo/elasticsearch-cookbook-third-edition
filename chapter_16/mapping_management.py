import elasticsearch

es = elasticsearch.Elasticsearch()

index_name = "my_index"
type_name = "my_type"

if es.indices.exists(index_name):
    es.indices.delete(index_name)

es.indices.create(index_name)
es.cluster.health(wait_for_status="yellow")

es.indices.put_mapping(index=index_name, doc_type=type_name, body={type_name:{"properties": {
    "uuid": {"type": "keyword", "store": "yes"},
    "title": {"type": "text", "store": "yes", "term_vector": "with_positions_offsets"},
    "parsedtext": { "type": "text", "store": "yes", "term_vector": "with_positions_offsets"},
    "nested": {"type": "nested", "properties": {"num": {"type": "integer", "store": "yes"},
                                                "name": {"type": "keyword", "store": "yes"},
                                                "value": {"type": "keyword", "store": "yes"}}},
    "date": {"type": "date", "store": "yes"},
    "position": {"type": "integer", "store": "yes"},
    "name": {"type": "text", "store": "yes", "term_vector": "with_positions_offsets"}}}})

mappings = es.indices.get_mapping(index_name, type_name)

print(mappings)

es.indices.delete(index_name)