var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });

        $('#btn-comment-save').on('click', function () {
            _this.comment_save();
        });

        $('#btn-comment-delete').on('click', function () {
            _this.comment_delete();
        });

        $('#btn-recomment-save').on('click', function () {
            _this.recomment_save();
        })
    }
    ,
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    comment_save : function () {
        var data = {
            username: $('#username').val(),
            content_c: $('#content_c').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'POST',
            url: '/api/v1/comments/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            window.location.href = '/posts/detail/'+id;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    comment_delete : function () {
        var id = $('#id').val();
        var comments_id = $('#comments_id').val()

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/comments/'+id+"/"+comments_id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('댓글이 삭제되었습니다.');
            window.location.href = '/posts/detail/'+id;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    recomment_save : function () {
        var data = {
            username: $('#username_re').val(),
            content_c: $('#content_c_re').val()
        };

        var id = $('#id').val();
        var comments_id = $('#comments_id').val()

        $.ajax({
            type: 'POST',
            url: '/api/v1/comments/'+id+'/'+comments_id+'/recomment',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            window.location.href = '/posts/detail/'+id;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

};

main.init();