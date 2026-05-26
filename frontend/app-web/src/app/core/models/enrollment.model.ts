export interface Enrollment {
  id: number;
  userId: number;
  courseId: number;
  enrolledAt: Date;
  progressPercent: number;
}