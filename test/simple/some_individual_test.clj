;; The contents of this file are subject to the LGPL License, Version 3.0.
;;
;; Copyright (C) 2013, Phillip Lord, Newcastle University
;;
;; This program is free software: you can redistribute it and/or modify it
;; under the terms of the GNU Lesser General Public License as published by
;; the Free Software Foundation, either version 3 of the License, or (at your
;; option) any later version.
;;
;; This program is distributed in the hope that it will be useful, but WITHOUT
;; ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
;; FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
;; for more details.
;;
;; You should have received a copy of the GNU Lesser General Public License
;; along with this program. If not, see http://www.gnu.org/licenses/.

(ns simple.some-individual-test
  (:use [tawny.owl])
  (:use [clojure.test])
  (:require [tawny.reasoner :as r])
  (:require [tawny.fixture])
  (:require [simple.some-individual :as s]))

(use-fixtures :once (tawny.fixture/namespace-and-reasoner
                     'simple.some-individual :hermit))

(deftest A
  (is
   (=
    #{s/i}
    (r/instances s/A)))
  (is
   (=
    #{s/i}
    (r/instances s/B)))
  (is
   (=
    #{}
    (r/instances s/C))))
