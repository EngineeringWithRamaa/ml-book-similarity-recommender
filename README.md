# Book Recommender System
A ML based Book Similarity Recommender for promoting books based on Book selling platform 

# Overview

  The Book Recommender System is a modern application designed to recommend books based on their textual content and vector similarity
  This project leverages the Bag of Words (BoW) model for tokenization and stores book data as vectors in DataStax Astra DB, a cloud - native database service optimized for scalable and efficient data management

# Key Features

  Textual Analysis with Bag of Words: Utilizes the Bag of Words model to tokenize and represent book descriptions and categories as vectors
  Vector Storage: Stores book vectors in Astra DB for efficient retrieval and similarity searching
  Similar Book Recommendations: Recommends books similar to a given book based on vector similarity, using Approximate Nearest Neighbors (ANN) search

# Technologies Used

Bag of Words Model: For tokenizing and converting book text data into numerical vectors
DataStax Astra DB: A cloud-native NoSQL database service for storing and managing vector data with minimal operational overhead
Spring Boot: Framework for building the RESTful API to interact with the recommendation system
Apache Cassandra: Underlying database technology for Astra DB, known for its high scalability and performance
Java: Programming language used for implementing backend logic

# How It Works

Tokenization with Bag of Words: The text data of books, such as titles and descriptions, is tokenized using the Bag of Words model. This converts textual data into numerical vectors representing word frequencies
Storing Vectors in Astra DB: These vectors are stored in Astra DB, allowing for efficient querying and similarity searches
Similarity Search: When a user requests recommendations for a specific book, the system retrieves the corresponding vector and performs an approximate nearest neighbor search to find and recommend similar books
Return Recommendations: The system returns a list of the top 3 most similar books based on vector similarity, excluding the original book from the results

# API Endpoint

* GET /promotions/books/{bookName}
* Description: Retrieves book recommendations similar to the one specified by {bookName}
* Response: A list of recommended books or a 404 error if no recommendations are found

# Setup and Installation

Clone the repository
Configure Astra DB credentials and set up your environment
Build and run the Spring Boot application to start the recommender service

# Contribution
Contributions to enhance the recommender system or add new features are welcome! Please fork the repository, make your changes, and submit a pull request

# License
This project is licensed under the MIT License. See the LICENSE file for details
