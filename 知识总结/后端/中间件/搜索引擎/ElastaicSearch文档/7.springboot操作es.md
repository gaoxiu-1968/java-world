# springboot 操作 es

## 操作索引

### 新建索引

```
		CreateIndexRequest request = new CreateIndexRequest("person");
		CreateIndexResponse createIndexResponse = rest.indices().create(request, RequestOptions.DEFAULT);
		System.out.println(createIndexResponse.toString());
```

