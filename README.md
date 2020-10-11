# SearchEngine_Indexing

Run -> bash Textprocessing.sh input output

Second part of building a tf*idf based search engine from scratch. 

This code reads tokenized documents, creates a trio of files dict, post and map

term -> token, documents -> files, term frequency -> frequency of token in that particular file

dict -> Unique terms in all documents, number of documents term has appeared, starting address in post

post -> doc_id, term frequency 

map -> doc_id, doc_name
