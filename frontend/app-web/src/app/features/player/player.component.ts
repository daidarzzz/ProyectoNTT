import { Component, inject, signal } from '@angular/core';
import { ProgressService } from '../../core/services/progress.service';

@Component({
  selector: 'app-player',
  standalone: true,
  template: `
    <div class="player-page">
      <h1>Reproductor de Curso</h1>

      <div class="video-container">
        <div class="video-placeholder" (click)="startPlayback()">
          @if (!playing()) {
            <span class="play-icon">▶</span>
            <p>Haz clic para comenzar</p>
          } @else {
            <p>🎬 Video en reproducción...</p>
          }
        </div>
      </div>

      <div class="progress-bar-wrapper">
        <div class="progress-bar" [style.width.%]="progress.percentage()"></div>
      </div>
      <p>Progreso: {{ progress.percentage() }}%</p>
    </div>
  `,
  styles: [`
    .player-page { max-width: 900px; margin: 2rem auto; padding: 0 2rem; }
    .video-container { background: #000; border-radius: 12px; padding: 4rem; text-align: center; color: white; margin-bottom: 2rem; }
    .video-placeholder { cursor: pointer; }
    .play-icon { font-size: 3rem; }
    .progress-bar-wrapper { background: #e0e0e0; border-radius: 8px; height: 12px; overflow: hidden; }
    .progress-bar { background: #e94560; height: 100%; transition: width 0.3s; }
  `]
})
export class PlayerComponent {
  readonly progress = inject(ProgressService);
  readonly playing = signal(false);

  startPlayback() {
    this.playing.set(true);
    this.progress.setDuration(3600);
    const interval = setInterval(() => {
      this.progress.tick(1);
      if (this.progress.percentage() >= 100) {
        clearInterval(interval);
      }
    }, 1000);
  }
}