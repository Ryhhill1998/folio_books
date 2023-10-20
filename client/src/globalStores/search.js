import { create } from "zustand";

const useSearchStore = create((set) => ({
  query: null,
  setSearchQuery: (searchQuery) =>
    set({
      query: searchQuery,
    }),
  increasePopulation: () => set((state) => ({ bears: state.bears + 1 })),
  removeAllBears: () => set({ bears: 0 }),
}));
