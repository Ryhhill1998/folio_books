import { create } from "zustand";
import { persist } from "zustand/middleware";

export const useProfileStore = create(
  persist(
    (set) => ({
      id: null,
      displayName: null,
      email: null,
      signedIn: false,
      loginError: null,
      registerError: null,
      registerUser: async (displayName, email, password) => {
        try {
          const res = await fetch("http://localhost:8000/users");
          const users = await res.json();

          const foundUserWithEmail = users.find((user) => user.email === email);

          if (foundUserWithEmail) {
            throw new Error("A user already exists with that email.");
          }

          const newUser = {
            id: users.length + 1 + "",
            displayName,
            email,
            password,
          };

          fetch("http://localhost:8000/users", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(newUser),
          });

          set({
            id: newUser.id,
            displayName: newUser.displayName,
            email: newUser.email,
            signedIn: true,
            loginError: null,
            registerError: null,
          });
        } catch (error) {
          set({ registerError: error });
        }
      },
      signInUser: async (email, password) => {
        try {
          const response = await fetch("http://localhost:8000/users");
          const users = await response.json();

          const foundUser = users.find(
            (user) => user.email === email && user.password === password
          );

          if (foundUser) {
            set({
              id: foundUser.id,
              displayName: foundUser.displayName,
              email: foundUser.email,
              signedIn: true,
              loginError: null,
              registerError: null,
            });
          } else {
            throw new Error(
              "Invalid email or password. Try registering instead."
            );
          }
        } catch (error) {
          set({
            id: null,
            displayName: null,
            email: null,
            signedIn: false,
            loginError: error,
          });
        }
      },
      signOutUser: () => {
        set({
          id: null,
          displayName: null,
          email: null,
          signedIn: false,
          loginError: null,
          registerError: null,
        });
      },
      clearErrors: () => set({ loginError: null, registerError: null }),
    }),
    {
      name: "profile-data",
    }
  )
);
