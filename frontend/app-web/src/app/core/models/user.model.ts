export interface User {
  id: number;
  name: string;
  email: string;
  avatarUrl?: string;
}

export interface LoginCredentials {
  email: string;
  password: string;
}