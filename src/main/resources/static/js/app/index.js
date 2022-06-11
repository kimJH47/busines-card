const main = {
    init: function () {

        const _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });
        $('#btn-find').on('click', function () {
            _this.find();
        });

    },
    find: function () {
        console.log("검색시작!~~");
        const type = $("#search option:selected").val();
        console.log(type);
        console.log($('#content').val());
        let data;
        switch (type) {
            case "name":
                data = {
                    name: $('#content').val(),
                    company: null,
                    tel: null,
                    email: null
                };
            case "company":
                data = {
                    name: null,
                    company: $('#content').val(),
                    tel: null,
                    email: null
                };
            case "tel":
                data = {
                    name: null,
                    company: null,
                    tel: $('#content').val(),
                    email: null
                };
            case "email":
                data = {
                    name: null,
                    company: null,
                    tel: null,
                    email: $('#content').val()
                };

        }
        console.log(data);
        $.ajax({
            type: 'GET',
            url: '/api/business-card/find',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (data){
            alert(data);
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },
    save: function () {
        const data = {
            company: $('#company').val(),
            name: $('#name').val(),
            tell: $('#tell').val(),
            role: $('#role').val(),
            email: $('#email').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/business-card',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('명함이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update: function () {
        const data = {
            name: $('#name').val(),
            company: $('#company').val(),
            role: $('#role').val(),
            tel: $('#tel').val(),
            email: $('#email').val(),
        };

        const id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/business-card/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('명함이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete: function () {
        const id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/business-card/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function () {
            alert('명함이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

main.init();