DROP TABLE IF EXISTS categoria;
DROP TABLE IF EXISTS autor;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS tags;

CREATE TABLE if not exists categoria(
    id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY not null,
    nombre VARCHAR(60) NOT NULL,
    imagen clob not null,
    is_activa boolean default false
);


CREATE TABLE if not exists  autor(
    id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY not null,
    nombre VARCHAR(60) unique NOT NULL,
    email VARCHAR(100) unique not null,
    avatar clob,
    password VARCHAR(80) not null,
    is_activo boolean DEFAULT true
 
);                                                   


CREATE TABLE if not exists post (
    id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY not null,
    titulo VARCHAR(100) NOT NULL,
    sub_titulo VARCHAR(100) NOT NULL,
    cuerpo clob NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL,
    categoria_id integer NOT NULL,
    autor_id integer not null,
    FOREIGN KEY (categoria_id) REFERENCES categoria(id),
    FOREIGN KEY (autor_id) REFERENCES autor(id)
);

CREATE TABLE if not exists tags(
    post_id integer, 
    tag VARCHAR(255),
     FOREIGN KEY (post_id) REFERENCES post(id)
);


insert into categoria (nombre, is_activa, imagen )values ('Literatura', true,'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAyCAYAAAA9ZNlkAAADXUlEQVRYhe1YPW7bMBj9qBPkAg3fEYyYH+ktmdsDtGOKdsrSDN0K1OkUo5OBAImX1F6axQFSdHF/BhlIhg4F7BvEN4hvkA4kZUqWZDtOageIgA8kqE/UI/n0+CiKoA8imNtVBa0VAAHdLwoCDwn6T1nOIlEIwNb1AYF3bZ07STK4GUHXaZM/CpiYZHUvgmkT1A4teM0AENZTADru3tADFTAjAROvDICfqUVn4f4AEFF2FvKILaD7DwYgNQtQOwQ+FNCDVEjdJVQ2HgZAMAuln1ywTPkApO6GiAX0gKQ69W1JXfJvAT0g8CGBtwm8TVJ9yB15ECTVG5+/nkJk6+VB4CZBtWbkdJxmFOV0cgFYMul6BNMUMLHngW+3Ksb9kB+BsvkX1rN8SQQMW/tTvMojYVmZByBL4BwAnfSLnwA8ASgC4IVI6m4STnAE9CACt0nyV5L6SEhzSZKP83Lsva3PAtwjycck1altU65NHxXowCMQotkiZQUoFCwCNwjcDgSsQIgWvVCDgL4hbO0LmGsHoOH0vpFYMMnnEcwtSf1XwMQEbiwHADUImGuS6sxyh68EzMiNduhJGBDUtw1LSTjv+92orlyn38PlWw4AuGL3gOmIYJp29GrH5qqWG/25gB4TKhvLA5DVvQjczg994qZ+FEl9Ztfe/HKj7xBZK7bsDOyWslzyewE9dnY86YhQw30BOCyeAW7TM35NUn0gWX3h3NDLlCtC9R1JPo7AbUL1k+8vaUvKdRcigmpZ4eBGntgIcBwKTMYJtfOWT4D7dk8oEaIo64Rk9S1J9VzAxMlxzImIs+Cv3Br3045YH3heEGrwJnTaVc/eDYcTok3vZOn6JOzz1rJnOPZ/ABB4V0DfrA6AVcvx5LNeAQB3co6XBMA/7gqAUIPLac4NgKAuBDhOSqm6JFXX18MyWw/Dng10nZyDyuaT1JdFn+FqhcgJUNmRKhSkDm2qk6JjWuKCpPqS02e+EAnoPkF9c4JTCT8j63CsEIX/j0LeeL8Q8sO6YtVKO6ESWy7A/TApS74yW55WxDueC5YBkF7fJwCPEQBBXRBUL094khKqlxKqjHhNiQ7Uz+k+uLeeQrQSANax2B+MAnoc8GE4+fG4fIQbkIAZJfeyJ56QkPOelOa5ivr+Bxwym9cxae+0AAAAAElFTkSuQmCC'),
('Música',true,'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACUAAAAyCAYAAADbTRIgAAACoklEQVRYhd2YwZGjMBBFW0TgEH4IHIzkozPYCYEM1iE4BGdgh0AGQwYmg3EGdgazhxYgGyS1mMFQqypuonjq7t/9EZFvId8o6GsGc/LueetioEZBPwj6Y2kcXtAfCvquYL6WRukXzCGD+c5gvpdG6ZaCqTooFPnSPETkQumj6AXkG8IOczIRYbvnKG338b1F2UY1g76sAiqDvrRQCuZGyDdrg6pWAmVOPZRuVgFFKPJeqeYwD1AqFBEp6Gb+npYOVf8fUCjytHTPnT4UuR34jRgsgz6mQCXPSRQltxJ9UTDVaqAUdM0RFk6B+aG2+zZSYhPZQ8kMXjrUDgrmpmC+CEXp2VTkLkALJXUJU7xXJw7fBFAwlesy3wQVbiPdBivPUaiA2ZsJqneaCuZTQd+foOx8GzVy7uz7TSjXk788Zy5KffWq0TbaGaD604ae8UjNBUVECuYWAvJ23slQ/L1gL4xBDZSIfEMwfzKYczCSntW/44PCDvH0uUrkn9VhemUTQATlWtpA+j7b/W09DKFsd7YDdzoU3xsMTh0qdB8Uw7NSYzMtAuVtB/4UCt6JpTK4L3TqsSg40X0E9tUhoCfFwvwdzD95lJ5lH67D8ZmZQR8DpXLu4OKtYCQCbGe9daigr6+nTxMTf8CbCudDD7Y4MmF0qe7LJPoNTmc7+LkRHsbqy/qek0R5oTSKyyOh13VRTak/BX1v0yiPVOKVUu+zEoRhT+7ao0BN3dKiRKmpe0mhqB9OuN+SKGgYKfb+knfdUSZf6TX1YBGljLLUQk+MVjug3Ys1QbSmXZPHCt72tJKjK7FFL4eZvLreZir7C17z9aI5PHXzpIH/UyjhkrSCBaDS28jsUKtMX8x7LQNFlOpEyvdAEVm1FqVVa+OCKOjaVe0/465jk18i57kAAAAASUVORK5CYII='),
('Cine',true,'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABkAAAAyCAYAAACpgnCWAAACwklEQVRYhbWX25GjMBBFW4qAEG4IfEDLn4TgDJYQnIHJwM5gnME4gyGDdQbjDMYZzH60xEOWxHOposplRJ9+SheiNRcO0DAfCvyjYX4VzLcGnwl5tsreO6CsnXH/VjDfhANmGMkzAv/R4LN4N3gJB8QAPYj/TnmZh42UFyIiDb6lAO4m8DEawYSXH5L7aYgG34IMDW7mGZi+FbgNQhS43QuiYa4RiLnvBSGUeazo9WQKpG6PVVEMUhY1oMCf0tblJbHmR4PPY+eLyosmzzTMdYd01Rp8FsfMr99hN43yomC+tnbXCDqOxJz2auEEJM8IfNw6M2lIn7ZNEFuTxsH+D2SUnbLeFeJGIGh0v0j4PBtCKKo+p9wsvedBiIhwQJdPFNV0sf3J9lKjwJ8ygOWl8wZl3Z3ZKHMF81TghwK/FLh1nWN/v+KbIskWbw20qYUK5k4oquFZYds11+DbdhFha0QwpyFEgV/u/2S65kHEiIK5e5DWRbis4IHLpcNCHu8QebYJMvS2mwORR61EycddILbj8i5dNn1WTV63FX8wmALsIa4OAivruN6ahIzTEYJIFHJErGKIgbJ20y91KKpRR4GPViO3qyBWKl2dJpbaFJU7MwSSZxp8WwexCl6BfwaRNVok6xfBnHpnuLUyNy2JwhC75Ywg1uvBlLv/VkDMyRXVgzRd2rq1RSVqZ2Hxe48H/d8NpQchqd/iushL3iR3cyO7wOT6GZDnW/jD4fQ/3WBOCuY575OuMxZQ6PZMCaZF9rPX/MmXs+MVehSFUCT62CVFD+c3BVk0lAr8iAmzpDIEH4fDm7ykHuECioAwzzAkz1LvDhYWVdQISZSplEgWporvJj1upE1B7CHWJBn+VrIU4kunmJFH6iidhMiaYPuPIYlL0pHebd35H346Q0PNEtb22E4sSKuOeZADNqnKpZ8I/wALrYDVU+3xwgAAAABJRU5ErkJggg=='),
('Deportes',true,'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAnCAYAAABNJBuZAAAFGUlEQVRYhdVZu24bRxS9s1/AD5A05wuMDbVzh0WC7CcQSJF0UhGkcSEWKZImZppAnQkkhZuAbCwYSAARCgLZjclCRYAUIuIqlZj8AJUvUIo7szu75D7oOKI8wGCX1A53zj33njkzIrrvBhMTzDGB+/f+7nfSkKQCoIf8s7t/b1oIoPz9e9MQdzamEUwcgZ8QTLyDWb1lA/cJccffK/B1BHsXwd4p8O3DSjHYgeQ997NiRpL6P0fgYQTz1AMIgKwU7HSHMw9aVeGihwwMzHEZRADm+mHUS5WUBikVgSdVQKTzJEu/nTVwn8BHUrz2JCtgH2XEHQVe1QERVvJU3AGIuKNgl+sTszcReCjPJGk9G1kf7U7FYI4V+HZzlO2NyG87IAp2ursVH3bQMMExIe4ECrXGXqlWhjvB0VzE9k5YEwAKPH94QGR1bgQiiiTyK6zw4v8Dgh4imKcKdibq00IKkaTNERYWHPBjBbusG1NQLiQpwZ5E4Ce+E/ioem4ustlqLDZi0aggNYXuindZAOICtmmMgl3miyf3RfUaaq84mbijwPPcXvgr90VFapgRwDVAJPJrFgQ9ROCJAs/lHfl6I0DttI3KiXPwwYYdiDcysdszxNJ7yHxTwFyZkaaUcsXevDZIQK/9uhQGqHzvmfapTeA+SUqts+F7Zh1gJhHsHR2YL/KXJ2ld4W7loZyMExF5pSsGNknD7oUhAg8V7A1JbvqHPRP5Dwj1SRrBvHATHIVRrFMtsegt/dNGINwPMuY4v+d+DsSOFOyS8vrwAz0z0rP81uZLBbuk/e5HxQmYeJMKZQHaoslceghEYuU9WngfKlyWWhLxHrLCyRiRa0FxaiPqx9kBIfmMwEd0YD8UuWy5cdq62MX+EBFRBDsqMuJzM6uRYeuQyiRm+Yu6g4ChWXtASdpsZYIUl0F+DfEs5IxIsbZ9uYnXqe8WfJgCr7ba1iJJfUH7LoGuqDuX4+OcmSSNYMcReNL2nRHMKWnzpg4IwVx4ia0H0APBfO339qTNFcF81RwEV9RugZrJlRft2RDFUTB/EMzLMhAFuyTNP3rGCHZQ91uk+TwA/6dfRxR4RXvdR7VzKURfbPeo+uliK6uWAl8rmEva++ATBX5VTrlKARHL81sA4h8F/qvE6Kp+bfJFLqCG20hntap0K/cq6++X7XDIqAJPg/tbpfnXgj2pnpCwsk1tEBGR5jOCGRPMJWlz5VJzTvvmU38vem9eEsyYNJ+tA0lSBf67aEVya0JIninNr4PvajJGvNN420OAKt2vYmTTOVYEHtLB4bfZWM2vC+M0P3ffn9WmpwCRLWn137nv1olxQXkqzGMVkE05njllzT+R5u/p4PC7gMmL3EnLdrkBiJixGhBh5AvUZk5XmzeE5FzBXNJ+97ECX5Pm5+SKuGoCTTtNpfmXIBCT+tPJGiBOnhduMgvxUiErcYdgTkOFIpjTAhPa/FC5hoD7FEx2M5M+GGbeoFzVQJxdDs3cYn183JHnfEGK9ZfPPKzfpMUdV+CvCHxRtiikzRXp5Gf3uy+qQVQByU7RZSfpJ9a4z4CJaa/7aCvhKJ1/Kdibonfjl+7dDUtDCGRDASvYmdL8uwKfFw8DzMfS38HZbY1hJCTP2q1vbm/hrpV78TZdrI6dFUHbkxx088GGS9O5pOVbnkD+FxBbAp6K+7YDOjDfkD783LneTeALXf7f0rDNiGBH9wVEwU5JJ2eiaua03aFf5gAWRET/AmYYBr0kYUH1AAAAAElFTkSuQmCC'),  
('Tecnología',true,'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACoAAAAyCAYAAAAqRkmtAAACmElEQVRoge2ZwXXCMAyG5UyQEf4RfCAyR0boCN2g3QA2KCOwAWwAG8AGsAFs0B7sFCexnUAUwmvRe74Yo3yRJUtWiCQFOlcw2wzmO0PxJapbUjLwykLaQZjMxmYKigLvqqDmc2ymsIDfSkgFvhCmGBspKhnM0lrziSGJiBTM5qn9sxQFPj+3fxIRYYprMPFqbJy4oHj3gmk/EoTO/aNHagziy/8clN9k6DCZDQHYHLx4gb5A/ziozoeH5ZVYEfPc1nyBDgrKi9SoX0PqPpj6r3waxWSmwGt70yy+CDovf1Iwx2SwELnChucKZqvA62GKaxS6DqBgjvah5RU5PBT47NY1XoZQaFHO+lVY9GiSlLQP3j8UeCdHWbliyA+xw77NB/tb1Wz7U3pNBaf0VF6Je8KdqlbtWUDXI9WC86L/djcMcOxhzapvlo4vAkqhXtWdvur7pgIfSkVSoIQpFPhwv6+6DBJ7WzHQwK5l4Lmf8ZLSPNyrRYMoaFBfKgmg0DYP87qp+NGg5tty8LyaYqHzsrkV+EOjzykN6nz1Enn++dcVFHgfWbQLFQ3ioG5HY2na9rFgPr2JQ23RMuQlg4DStQkc4qHyLRTMpr64nOuitPr2lKwPCMV7SGct4y39OcpglvaM1Lkz/9lBnlLHRKzsU+BLO2gkXdrPP6erbxbaxc/BXYencBP7awC1FLReLzRm0ViAJjOQNdblV5czYOXBCrxTMJsuVXeqkLb64r2A2NZXYc3GBXN6bat0sGgc9IFfTFKlngVNWVSqJ9pFohZ1hUUiiTzGotA5wXzEILpY1AaJ+ehcfNwOmQbsCuoBn+XdwMtgraCY4rb1go2ILpb0H3xLmk1lwNsk0CVJgxbansvVC1zCBXZtCD+xqRSn84B8TgAAAABJRU5ErkJggg=='),
('Developers',true,'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAvCAYAAAChd5n0AAAC9klEQVRogdWZ23XiMBCGR6qADvYvQQ/WDG9xCekAztkCwlawdJB0gDsIHUAH0AF0AB1kH2QTQ3yRQLLZOUdvQZnPcx8RPZPAGg37riAbDVkReEYwk7HV8hPIG0HeFHinIV+3R4FPGvz36YEUeN8E0ARE4Nex9W0XyMIHpDqEKcZWuVEUeBsCosHLsXVulDAI+VLgbfttsEZDVgpyUOBPF1z2ZYgAiwqiwcVYpoxsEX5t/6EcnglEQz46QMykO1NYEx/AvrsaEQbSm7W6sgdBFrFBFPgcDmHnvRdr8LLDL3exQQjWKMjRPzZk431x99dIk8ECLeLn4l3m9jLrHRJWELnovxFZ3hV8CrJOAUIwEwVZ32UVZLlrOitvgZ33+yifkoDUlPK1DsHOCVl+HT+YwjcNErI8LcwUfoHPp1srkoZ8BARcRzF6FMJMCDwLCf6aRY7kOw+UP4hf5cEz19+FA9QstA1uEaLNAzCTtqkw/HBBN2TnvsB/qMrDTFxXbV8I8hYHQr40eElVpighDFF3bu/uPpuU51m5TIikdNPH5dd6C/8dyD3jp+/Xf9T3/UGy/NLCX6XWm3ZFgc8KsibIgpD9UZDNzTmUf7dz7hPT/31AyoJYd6s6DCHL6Vf2u/TnVaVw11Hg3ZAQVx6iwcsfxa5WOZ/5XMesyybmfwG5rn19jaQniHPLKXz6tTiHCzfL8F6Bt2HjBaZwnWaWO4V56dxRFvWLqv5HQY4uhXNRDmwfMVLw5aMNJrCm3Od+3jOXt50UI3eD8jzTkFVMxRss8pkQwJr06ZaLKsDTuJYrfMkscAnu7/91TjMTBW7S74YoxWWpBCDhm/SQwP657Ggs3DFEh02XIUG9+16k25d6V57ItYar/mUPt48PUUrIKidCzCyTgVTZZACIIh3EBab9WeLGPbahx/VWAz6C+riY/yJ6TPF0sRRvLdFFQ1Y9Fkn6+vW4uE3JwSNGzmOr2iphDeOTvpET+T3UuNh5Ygii7jblAjDAe/0//tySAyBd5RQAAAAASUVORK5CYII='),
('Noticias',true,'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAtCAYAAADsvzj/AAAB6klEQVRoge2ZwXHCQAxFJVfgEn4Je2C13KCD0IHpIHQAHdACHUAH0EHoADqADsjBhMTD2tgKzu4y+TO6cMD/jVaWVibqSxiMGbJmuO1XZLBLgjW9PfPpgp1mcJe6YLjDFWpEMDlBitCWvWqCqIvQnu+FIdIHgUw0ENGBMORDDYLBOLT/m7QQLwYS0WtZC8Fwx9DeK2LIWQcji9DeK8ogq5cAIQyhy0psIEREsOY1QEhT9P8g/YrhNsn2kIowGLfvIbILbbdRbV7FDDkThgjt9aEyyKIBYp8ExE3e+4msQttqJ1hTXmOlyCBzz2y1LX+XOcG9xZUZSMGQtW5EcReGfBCkIJi8Z6N25A+ZMOSkBfAAnQjuvfqMNnCVI2CX5epGf9PrOxjucPW4Lo+mFAQ7oq7NK8ZguM3DHVQKQbBTIphcfyEKH2VTvdaQ/kIUQ/zsSR1mo9jibuBkuGNoU13Dv7SAm4U21j0bbuYB0e1sw4LUjDYp9ZSyd9R2ef0S+u+zIZMGEJOHNtguG3JugLDTZw5/PR+rQ/3on2Av8b+1iIgh+9DmuoHUbV9gDUP2KTRHhuzr6ySJopdVubhouQuLc5DULC0iLH7157mYauVXX7WaFmwBjtVCDdI0SDLckSG77+ievS7/8Wj39QkDJYTfM8OedwAAAABJRU5ErkJggg=='),
('Tutoriales',true,'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAhCAYAAACbffiEAAAFHUlEQVRYhcVZQXIqOQyV+wQcYJLoBFNd+bTE4v8aDjBV9G4WswirWfxN2GYVbhBuADcIN4AbkBvADegb/FlIarvdbgNJ1YyrqArYliVZek9yALAcAVZ/JD4l2MCqTK8pRzI/wfT8BL2M8TQr45qR1QPH0wL5V/xxSHvb75D2qTWA4ykAQIG0TM0XSEuTkZ73Mr6ux39kiENq0jL05of1OHzJEBHCR4e8ldApRwXSRjZSreH0ImvoMKDE2SHvCuSVhZlD+hAZ5UhllA7poOuSehTIa4d0MD0c8jZriEPemucc8gmwmgNSXSAtAfnZIR9NcIG8hvvqnwJpE3oq+vujQNoA8kKcQK8O6Szz1ZtDeoffHn+XPeIk3Rff3kocWs0B6Sl2XMcQh3TWWF59NrRsj1fqczmiADS/MUeq0iHtHdJewodXumEVKLGSNXwyT8t6ie/AkKZ7YCdH9vppTMFQRteQqjRnhIZ4PVoZHT38QF70fwznqXbIx8F5ALA4HppWZQ5D81cNpLpA/jU4H3uxL6CaO6Qmp6h4izbDZ9DSIX3k9FSPrwYXIC/6egSEKMlMrwlCnMvvFs+8kO8B4XklPrJKXDEkXHhx266beIQ2mkfXktnZn1PNHfLOIb2LTN465B1gNc/L8KGs6LUTFJ2gyOOdOD1nSIvbYohDegfkmTeEaikbBmU0XokusvUA4QoZ8Z4LhKjxjRMcgrs+/FUvnnOMg8ZT+W08FQ6id7slJbizrt0B8jPcP/4pYMMLL4NqkwvIM+Mx4ZHqLWuI5YlcH9WCVMLGFv+B0Us5XHDfbsshHzXP5kqYzVCJ4udoo8aXpqxExHgqawT6MzxyU6210vg+3ZQjyk/hbZlzxBnlKJsjUXkjLC+3nyREO2iIEMX7E9RSJklml3NEktd7+FM5sgGcYA90/EKtr4YGVqXEdB92g8M+klykYQJ3jz9aeMfxtJXV9jblqIXwmK+s7rur/rIcbG80UqLJG6KMmjckS4hWQ/W8GIw2fAbP4K1DPsWCS4/HE9QyZBeGlhGiIU+BvO4SpMgokNeAE4SHbz87HAEQeDxyRnATgPwMyDO4e/wB9/y9T8zSZTqkd5UvHaJN+li7reqM49u8lKp+r5WhcF56GO4UjWnUigWIpVLrO6QzIM8A+VkSVWDUYNgh7eHh8W9AegrI7qw3ZzIO4sXxNG6GuhAu+eMN4ZnxRFtgBsl90ZAE9J1SyANIdfj90qdFOEU8VXZhye+dMKjHURwrYRZzSiK0qO7gfesJ2kg72rLxUUnvJQqDpkug1iYrYiHPwjwDpCcxRIg1UHwb9D6NMvtCkIyeLt6INjSdmui2HLEuM5UjmdC6MlcvhJaVF9XcPwhEDZbid9GWJ7QULxpqTbAtV4L1IcRedkbYqYoOMTEPdpnxkGvM9AEtB+QJ8YKM0nsxNT+eatgOdqqFPUbklcgze5Fi3J6MnCGSC4OKKjrl22Xa9LtM8cAufGKJCbFINjR08LWWJ9UADNomSg3cC4lR7c/kXeCkeaiH07eu0LF5Gbd1iEv93ojX8tVvGEIhMlp4deevf3a1B8Isj/iHOWly5EGsbWjOrvM4Vr0VyGu45++CSu0TTuPBgZYhZMYvk0q4r/Dw7afcfNv77BVua9OjK8O/TA4YQgdFsjLE8wGiOrWFZvus2mXjS02RKS5Vse9bPIqWo4uN1e2hFQnIhJbdXhwWfRmS1PpMlGB3X6b/L4YM5cjw/Bf+NQEA/wILYAh/VOHlygAAAABJRU5ErkJggg==');

