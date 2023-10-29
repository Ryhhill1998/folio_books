import { create } from "zustand";
import { persist } from "zustand/middleware";

export const useBooksStore = create(
  persist(
    (set) => ({
      books: {
        romanceBooks: null,
        crimeMysteryThrillerBooks: null,
        scienceFictionBooks: null,
        graphicNovelBooks: null,
      },
      getBooks: async () => {
        try {
          const response = await fetch("http://localhost:8080/get-books");

          if (response.status === 200) {
            const books = await response.json();

            const romanceBooks = books?.filter(
              ({ genre, stockQuantity }) =>
                genre === "romance" && stockQuantity > 0
            );
            const crimeMysteryThrillerBooks = books?.filter(
              ({ genre, stockQuantity }) =>
                genre === "crime_mystery_thriller" && stockQuantity > 0
            );
            const scienceFictionBooks = books?.filter(
              ({ genre, stockQuantity }) =>
                genre === "science_fiction" && stockQuantity > 0
            );
            const graphicNovelBooks = books?.filter(
              ({ genre, stockQuantity }) =>
                genre === "graphic_novels" && stockQuantity > 0
            );

            set({
              books: {
                romanceBooks,
                crimeMysteryThrillerBooks,
                scienceFictionBooks,
                graphicNovelBooks,
              },
            });
          }
        } catch (error) {
          console.error(error);
        }
      },
      clearErrors: () => set({ loginError: null, registerError: null }),
    }),
    {
      name: "books-data",
    }
  )
);
