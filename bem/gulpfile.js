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
        cssOut: 'css/',
        jsOut: 'js/',
        htmlSrc: 'pages/spreadsheet.html',
        levels: ['common.blocks'],  // блоки с представлением в DOM
        model: ['model.blocks'] // блоки без представления в DOM
    },

    /**
     * returns {
      css: arr of all css classes that used in the file
      dirs: arr of the dirs in that are css classes are placed
     }
     Docs: https://github.com/dab/html2bl/blob/master/index.js
     * */
    getFileNames = require('html2bl').getFileNames({
        htmlSrc: params.htmlSrc,
        levels: params.levels
    });

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
            .pipe(gulp.dest(params.out + params.cssOut))
            .pipe(reload({stream: true}));
    }).done();
});

gulp.task('js', function () {
    getFileNames.then(function (src) {
        return src.dirs.map(function (dirName) {
            return path.resolve(dirName) + '/*.js';
        });
    }).then(function (jsGlobs) {
        params.model.forEach(function (folder) {
            jsGlobs.push(path.resolve(folder + '/**/*.js'));
        });
        return jsGlobs;
    }).then(function (jsGlobs) {
        console.log(jsGlobs);
        gulp.src(jsGlobs)
            .pipe(concat('app.js'))
            .pipe(gulp.dest(params.out + params.jsOut))
            .pipe(reload({stream: true}));
    }).done();
});