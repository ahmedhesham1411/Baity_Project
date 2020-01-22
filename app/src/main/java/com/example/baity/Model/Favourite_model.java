package com.example.baity.Model;

public class Favourite_model {

        private String title,description;
        private int image;

        public Favourite_model(String title, String description, int image){
            this.title=title;
            this.description=description;
            this.image=image;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }
}
