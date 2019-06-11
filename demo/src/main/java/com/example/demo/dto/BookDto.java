package com.example.demo.dto;

public class BookDto {
	    private int id;

	    private String name;
	    private String publishers;


		@Override
		public String toString() {
			return "Book [id=" + id + ", name=" + name + ", publishers=" + publishers + "]";
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPublishers() {
			return publishers;
		}

		public void setPublishers(String publishers) {
			this.publishers = publishers;
		}

}
