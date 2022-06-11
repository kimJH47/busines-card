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
        $('#btn-register').on('click', function () {
            _this.registry();
        });

    },
    registry: function (){

    },
    find: function () {
        var type = $('#search option:selected').val();
        let data = {
            name: '123',
            company: null,
            tel: null,
            email: null
        };
        if (type == 'name') {
            data = {
                name: $('#content').val(),
                company: null,
                tel: null,
                email: null
            };
        } else if (type == 'company') {
            data = {
                name: null,
                company: $('#content').val(),
                tel: null,
                email: null
            };
        } else if (type == 'tel') {
            data = {
                name: null,
                company: null,
                tel: $('#content').val(),
                email: null
            };
        } else if (type == 'email') {
            data = {
                name: null,
                company: null,
                tel: null,
                email: $('#content').val()
            };
        }
        $.ajax({
            type: 'POST',
            url: '/api/business-card/find',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
        }).done(function (data) {
            console.log(JSON.stringify(data))
            var tblresult = data;
            var str = "";
            $("tr:has(td)").remove();
            $.each(tblresult, function (i) {
                str += "<TR>"
                str += '<TD id="id">' + tblresult[i].id + '</TD><TD id="company">' + tblresult[i].company + '</TD><TD id="name">' + tblresult[i].name + '</TD><TD id="role">' +tblresult[i].role + '</TD><TD id="tel">'
                +tblresult[i].tel + '</TD><TD id="email">' +tblresult[i].email + '</TD>' + tblresult[i].upload + '</TD><TD>'
                str += '</TR>'
            });
            $("#result").append(str);
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