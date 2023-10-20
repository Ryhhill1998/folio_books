import { create } from "zustand";

export const useSearchStore = create((set) => ({
  query: null,
  active: false,
  setSearchQuery: (searchQuery) => {
    set({
      query: searchQuery,
    });
  },
  activateSearch: () => {
    set({ active: true });
  },
  deactivateSearch: () => {
    set({ active: false });
  },
}));
