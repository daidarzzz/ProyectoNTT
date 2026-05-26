export interface Course {
  id: number;
  slug: string;
  title: string;
  description: string;
  thumbnailUrl: string;
  price: number;
  category: Category;
  instructor: string;
  durationMinutes: number;
  rating: number;
  studentsCount: number;
}

export interface Category {
  id: number;
  name: string;
  slug: string;
}