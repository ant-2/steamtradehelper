var
    gulp = require('gulp'),
    concat = require('gulp-concat'),
    rename = require('gulp-rename'),
    browserSync = require('browser-sync').create('bemServer'),
    reload = browserSync.reload,
    path = require('path'),
    url = require('gulp-css-url-adjuster'),
    autoprefixer = require('autoprefixer'),
    postcss = require('gulp-postcss');

var
    params = {
      out: '../webapp/',
      htmlSrc: 'pages/spreadsheet.html',
      levels: ['common.blocks']
    },
    getFileNames = require('html2bl').getFileNames(params); // serves as a more convenient way to get all files that are placed in the specific path

gulp.task('default', ['build', 'refresh']);

gulp.task('build', ['html', 'css', 'js']);

gulp.task('refresh', function () {
  gulp.watch('pages/*.html', ['html']); // the path are relative to gulpfile.js
  
  gulp.watch(params.levels.map(function (level) {
    return level + '/**/*.css';
  }), ['css']);
  
  gulp.watch(params.levels.map(function (level) {
    return level + '/**/*.js';
  }), ['js']);
});

gulp.task('html', function () {
  gulp
      .src(params.htmlSrc)
      .pipe(rename('spreadsheet.html'))
      .pipe(gulp.dest(params.out))
      .pipe(reload({stream: true}))
});

gulp.task('css', function () {
  getFileNames.then(function (files) {
    return gulp.src(files.css)
        .pipe(concat('styles.css'))
        .pipe(postcss([autoprefixer()]))
        .pipe(gulp.dest(params.out))
        .pipe(reload({stream: true}));
  })
      .done();
});

gulp.task('js', function () {
  getFileNames
      .then(function (src) {
        return src.dirs.map(function (dirName) {
          return path.resolve(dirName) + '/*.js';
        });
      })
      .then(function (jsGlobs) {
        gulp.src(jsGlobs)
            .pipe(concat('app.js'))
            .pipe(gulp.dest(params.out))
            .pipe(reload({stream: true}));
      })
      .done();
});